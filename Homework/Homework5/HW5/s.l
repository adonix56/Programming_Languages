; part 1

(defun double-recursive (y)
    (cond
    ;template is ( (conditional exp) (block) )
    ;translation if (condexp) {block}
    ;            else if (condexp) {block}
        ((null y) nil)
        ((listp y) (cons (car y) (cons (car y) (double-recursive (cdr y)))))
    )
)

(defun double-iterative (y)
    (setq a nil)
    (cond
        ((listp y) (do
            ;template is (variable bind) (termination) (block)
                ((x y (setq x (cdr x))))
                ((null x) a)
                (push (car x) a)
                (push (car x) a)
            )
        )
    )
    (reverse a)
)

(defun double-mapcar (y)
    (cond
        ((listp y) (apply #'append (mapcar 'list y y)))
    )
)

;part 2

(defun rdouble (y)
    (cond
        ((null y)nil)
        ((listp y) (cond
            ((listp (car y)) (cons (rdouble (car y)) (rdouble (cdr y))))
            ((numberp (car y)) (cons (car y) (cons (car y) (rdouble (cdr y)))))
        ))
    )
)

;part 3

(defun my-mapcar (fun l1 l2)
    (cond
        ((null l1) nil)
        ((null l2) nil)
        (t (cons (funcall fun (car l1) (car l2)) (my-mapcar fun (cdr l1) (cdr l2))))
    )
)

;part 4

(defun rewrite (x)
    (cond
        ((null x) nil)
        ((or (numberp x) (symbolp x)) x)
        ((listp x) (cond
            ((equal 'if (car x)) (cond
                ((= 4 (length x)) (cons 'cond (cons (list (rewrite (cadr x)) (rewrite (caddr x))) (list (list 't (rewrite (cadddr x)))))))
                ((= 3 (length x)) (cons 'cond (list (rewrite (cdr x)))))
            ))
            (t (cons (cond
                ((listp (car x)) (rewrite (car x)))
                (t (car x))
            )
            (rewrite (cdr x))))
        ))
    )
)

;part 5

(defun check (x)
    ((lambda (arg) (reverse arg))
        (list
            (eval (rewrite x))
            (eval x)
            (equal (eval x) (eval (rewrite x)))
        )
    )
)

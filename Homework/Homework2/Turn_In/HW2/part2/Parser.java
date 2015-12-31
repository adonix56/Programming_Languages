/* *** This file is given as part of the programming assignment. *** */

public class Parser {


    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
        tok = scanner.scan();
    }

    private Scan scanner;
    Parser(Scan scanner) {
        this.scanner = scanner;
        scan();
        program();
        if( tok.kind != TK.EOF )
            parse_error("junk after logical end of program");
    }

    private void program() {
        //System.err.println("MADE IT TO PROGRAM"); //DEBUG CODE
        block();
    }

    private void block() {
        // you'll need to add some code here
        //System.err.println("MADE IT TO BLOCK"); //DEBUG CODE
        if ( is(TK.VAR) )
            declarations();

        if ( is(TK.ID) || is(TK.PRINT) || is(TK.IF) || is(TK.DO) || is(TK.FA) )
            statementlist();
    }

    private void declarations() {
        //System.err.println("MADE IT TO DECLARATION"); //DEBUG CODE
        mustbe(TK.VAR);
        while( is(TK.ID) ) {
            scan();
        }
        mustbe(TK.RAV);
    }

    // you'll need to add a bunch of methods here

    private void statementlist() {
        //System.err.println("MADE IT TO STMT LIST"); //DEBUG CODE
        while ( is(TK.ID) || is(TK.PRINT) || is(TK.IF) || is(TK.DO) || is(TK.FA) ) {
            stmt();
        }
    }

    private void stmt() {
        //System.err.println("MADE IT TO STMT"); //DEBUG CODE
        //System.err.println(tok.toString()); //DEBUG CODE
        if ( is(TK.ID) )
            assign();
        else if ( is(TK.PRINT) )
            eprint();
        else if ( is(TK.IF) )
            eif();
        else if ( is(TK.DO) )
            edo();
        else
            fa();
    }

    private void assign() {
        //System.err.println("MADE IT TO ASSIGN"); //DEBUG CODE
        mustbe(TK.ID);
        mustbe(TK.ASSIGN);
        expr();
    }

    private void eprint() {
        //System.err.println("MADE IT TO PRINT"); //DEBUG CODE
        mustbe(TK.PRINT);
        expr();
    }

    private void eif() {
        //System.err.println("MADE IT TO IF"); //DEBUG CODE
        mustbe(TK.IF);
        gcommands();
        mustbe(TK.FI);
    }

    private void edo() {
        //System.err.println("MADE IT TO DO"); //DEBUG CODE
        mustbe(TK.DO);
        gcommands();
        mustbe(TK.OD);
    }

    private void fa() {
        //System.err.println("MADE IT TO FA"); //DEBUG CODE
        mustbe(TK.FA);
        mustbe(TK.ID);
        mustbe(TK.ASSIGN);
        expr();
        mustbe(TK.TO);
        expr();
        if (is(TK.ST)){
            scan();
            expr();
        }
        cmds();
        mustbe(TK.AF);
    }

    private void gcommands() {
        //System.err.println("MADE IT TO GCOMMANDS"); //DEBUG CODE
        if ( is(TK.LPAREN) || is(TK.ID) || is(TK.NUM) ) {
            gcmd();
            while (is(TK.BOX)) {
                scan();
                gcmd();
            }
            if (is(TK.ELSE)) {
                scan();
                cmds();
            }
        }
        else
            parse_error( "Incorrect Token at gcommands()" );
    }

    private void gcmd() {
        //System.err.println("MADE IT TO GCMD"); //DEBUG CODE
        if ( is(TK.LPAREN) || is(TK.ID) || is(TK.NUM) ) {
            expr();
            cmds();
        }
        else
            parse_error( "Incorrect Token at gcmd()" );
    }

    private void cmds() {
        //System.err.println("MADE IT TO CMDS"); //DEBUG CODE
        mustbe(TK.ARROW);
        block();
    }

    private void expr() {
        //System.err.println("MADE IT TO EXPRESSION"); //DEBUG CODE
        if ( is(TK.LPAREN) || is(TK.ID) || is(TK.NUM) ) {
            simple();
            if ( is(TK.EQ) || is(TK.LT) || is(TK.GT) || is(TK.NE) || is(TK.LE) || is(TK.GE) ) {
                relop();
                simple();
            }
        }
        else
            parse_error( "Incorrect Token at expr()" );
    }

    private void simple() {
        //System.err.println("MADE IT TO SIMPLE"); //DEBUG CODE
        if ( is(TK.LPAREN) || is(TK.ID) || is(TK.NUM) ) {
            term();
            while( is(TK.PLUS) || is(TK.MINUS) ) {
                scan();
                addop();
                term();

            }
        }
        else
            parse_error( "Incorrect Token at simple()" );
    }

    private void term() {
        //System.err.println("MADE IT TO TERM"); //DEBUG CODE
        if ( is(TK.LPAREN) || is(TK.ID) || is(TK.NUM) ) {
            factor();
            while ( is(TK.TIMES) || is(TK.DIVIDE) ) {
                scan();
                multop();
                factor();
            }
        }
        else
            parse_error( "Incorrect Token at term()" );
    }

    private void factor() {
        //System.err.println("MADE IT TO FACTOR"); //DEBUG CODE
        if (is(TK.LPAREN)) {
            mustbe(TK.LPAREN);
            expr();
            mustbe(TK.RPAREN);
        }
        else if (is(TK.ID))
            scan();
        else if (is(TK.NUM))
            scan();
        else
            parse_error( "Incorrect Token at term()" );
    }

    private void relop() {
        //System.err.println("MADE IT TO RELOP"); //DEBUG CODE
        if (is(TK.EQ)) {
            scan();
        }
        else if (is(TK.LT)) {
            scan();
        }
        else if (is(TK.GT)) {
            scan();
        }
        else if (is(TK.NE)) {
            scan();
        }
        else if (is(TK.LE)) {
            scan();
        }
        else if (is(TK.GE)) {
            scan();
        }
    }

    private void addop() {
        //System.err.println("MADE IT TO ADDOP"); //DEBUG CODE
        if (is(TK.PLUS)) {
            scan();
        }
        else if (is(TK.MINUS)) {
            scan();
        }
    }

    private void multop() {
        //System.err.println("MADE IT TO MULTOP"); //DEBUG CODE
        if (is(TK.TIMES)) {
            scan();
        }
        else if (is(TK.DIVIDE)) {
            scan();
        }
    }

    // is current token what we want?
    private boolean is(TK tk) {
        return tk == tok.kind;
    }

    // ensure current token is tk and skip over it.
    private void mustbe(TK tk) {
        if( ! is(tk) ) {
            System.out.println( "mustbe: want " + tk + ", got " +
                                    tok);
            parse_error( "missing token (mustbe)" );
        }
        scan();
    }

    private void parse_error(String msg) {
        System.out.println( "can't parse: line "
                            + tok.lineNumber + " " + msg );
        System.exit(1);
    }
}

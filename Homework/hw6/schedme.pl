course(140,  programming_languages,   [olsson, pandey]).
course(141,  programming_languages,   [olsson]).
course(142,  compiler_construction,   [pandey]).
course(240,  programming_languages,   [levitt]).
course(150,  operating_systems,       [levitt]).
course(122,  algorithms,              [gusfield,rogaway]).
course( 60,  data_structures,         [rogaway,davis]).
course( 20,  discrete_structures,     [gusfield,levitt,davis]).

/*All course numbers*/

c_numbers(N) :-
	course(N,_,_).

/*All programming languages course numbers*/

c_pl(N) :-
	course(N, programming_languages,_).

/*All non programming languages course numbers*/

c_notpl(N) :-
	course(N,_,_),
	\+ c_pl(N).

/*Teachers teaching 60*/

c_inst60(L) :-
	course(60,_, L).

/*Sorted teachers teaching 60*/

c_inst60_sorted(L) :-
	c_inst60(L),
	sort(L).

/*Teachers teaching 20*/

c_inst20(L) :-
	course(20,_, L).

/*Sorted teachers teaching 20*/

c_inst20_sorted(L) :-
	c_inst20(L),
	sort(L).

/*Sorted teachers teaching N*/

c_inst_sorted(N,L) :-
	course(N,_,L),
	sort(L).

/*Course numbers with exactly 1 instructor*/

c_single_inst(N) :-
	course(N,_,L),
	length(L,1).

/*Course numbers with more than 1 instructor*/

c_multi_inst(N) :-
	course(N,_,L),
	\+ length(L,1).

/*Course numbers for exclusive instructor*/

c_exclusive(I,N) :-
	course(N,_,L),
	length(L,1),
	memberchk(I,L).

/*Course numbers with exactly 1 or 2 instructors OR*/

c_12_inst_1or(N) :-
	(course(N,_,L),
	length(L,1)) ; 
	(course(N,_,L),
	length(L,2)).

/*Course numbers with exactly 1 or 2 instructors (2 rules)*/

c_12_inst_2wo(N) :-
	c_single_inst(N).

c_12_inst_2wo(N) :-
	course(N,_,L),
	length(L,2).

/*--------------------------------------------*/
/*Part 2*/
/*--------------------------------------------*/

delete_question("gprolog's delete follows the behavior on page 188 of the 5th edition").

sortappend(X,Y,Z) :-
	append(X,Y,L),
	sort(L,Z).

/*--------------------------------------------*/
/*Part 3*/
/*--------------------------------------------*/

distribute(W,[],Y) :- Y = [].
distribute(W,X,Y) :-
	X = [H|T],
	distribute(W,T,U),
	Y = [[W,H]|U].

/*--------------------------------------------*/
/*Part 4*/
/*--------------------------------------------*/

myfor(L,U,Result) :-
	L =< U,
	L1 is L+1,
	myfor(L1,U,Res1),
	Result = [L|Res1].
myfor(L,U,[]) :- L>U.

crossdistribute([],Y,Z) :- Z = [].
crossdistribute(X,Y,Z) :-
	X = [H|T],
	distribute(H,Y,Res1),
	crossdistribute(T,Y,Res2),
	append(Res1,Res2,Z).

crossmyfor(R,H,Z) :-
	/*(R < 1 -> Z = []);
	((H < 1 -> Z = []);*/
	myfor(1,R,X),
	myfor(1,H,Y),
	crossdistribute(X,Y,Z).

/*--------------------------------------------*/
/*Part 5a*/
/*--------------------------------------------*/

getallmeetings(C,Z) :-
	C = [H|T],
	sort(T,Z).

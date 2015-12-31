/*Part 1 functions*/
c_numbers(N):-
	course(N,_,_).

c_pl(N):-
	course(N, programming_languages,_).

c_notpl(N):-
	course(N, X, _),
	X \= programming_languages.

c_inst60(L):-
	course(60, _, L).

c_inst60_sorted(L):-
	course(60, _, L1),
	sort(L1, L).

c_inst20(L):-
	course(20, _, L).

c_inst20_sorted(L):-
	course(20, _, L1),
	sort(L1, L).

c_inst_sorted(N, L):-
	course(N, _, L1),
	sort(L1, L).

c_single_inst(N):-
	course(N, _, L),
	L = [_].

c_multi_inst(N):-
	course(N,_,L),
	L = [_,_|_].

c_exclusive(I,N):-
	course(N,_,[I]).

c_12_inst_1or(N):-
	course(N, _, [_]) ; course(N, _, [_,_]).

c_12_inst_2wo(N):-
	course(N,_,[_]).

c_12_inst_2wo(N):-
	course(N,_,[_,_]).

/*part 2*/
delete_question("page 157").

sortappend(X, Y, Z):-
	append(X,Y,Z),
	sort(Z).

/*part 3*/
distribute(_,[],[]).

distribute(X,[H|T],Z):-
	distribute(X, T, Z1),
	Z = [[X, H]| Z1].

/*part 4*/
myfor(L,U,Result):-
	L =< U,
	L1 is L+1,
	myfor(L1,U,Res1),
	Result=[L|Res1].

myfor(L,U,[]):-
	L > U.

crossmyfor([],_,[]).

crossmyfor([RH|RT],H,Z):-
	distribute(RH, H, Z1),
	crossmyfor(RT, H, Z2),
	append(Z1, Z2, Z).

crossmyfor(R,H,Z):-
	integer(R),
	integer(H),
	myfor(1, R, X),
	myfor(1, H, Y),
	crossmyfor(X, Y, Z).

/*part 5a*/
getallmeetings([],[]).

getallmeetings([[_,H]|T], Z):-
	getallmeetings(T, Z1),
	append(H, Z1, Z2),
	sort(Z2, Z).

/*part 5b*/
participantsHelper([],M,M).

participantsHelper([H|T],M,Z):-
	addtomeeting(H, M, Z1),
	participantsHelper(T, Z1, Z).

participants(C, Z):-
	getallmeetings(C, M),
	participantsHelper(C, M, Z).

addtomeeting(_, [], []).

addtomeeting(S, [H|T], Z):-
	atom(H),
	S = [Name,MyMeetings],
	member(H, MyMeetings),
	addtomeeting(S, T, Z1),
	Z = [[H, [Name]]|Z1].

addtomeeting(S, [H|T], Z):-
	atom(H),
	S = [_,MyMeetings],
	\+member(H, MyMeetings),
	addtomeeting(S, T, Z1),
	Z = [[H, []]|Z1].
	

addtomeeting(S, [[Meeting, People]|T], Z):-
	S = [Name,MyMeetings],
	member(Meeting, MyMeetings),
	P1 = [Name|People],
	sort(P1, P),
	addtomeeting(S, T, Z1),
	Z = [[Meeting, P]|Z1].

addtomeeting(S, [[Meeting, People]|T], Z):-
	S = [_,MyMeetings],
	\+member(Meeting, MyMeetings),
	addtomeeting(S, T, Z1),
	Z = [[Meeting, People]|Z1].

/*Part 5c*/
osched(_,_,[],[]).

osched(MR,MH,C,Z):-
	C \= [],
	crossmyfor(MR, MH, Slots),
	participants(C, Meetings),
	length(Meetings, I),
	selectSlotSubset(Slots, I, Slots1),
	oschedHelper(Slots1, Meetings, Z).

/*Helper to select a subset of meeting times and rooms equal in size
to the number of meetings.*/
selectSlotSubset(_, 0, []).

selectSlotSubset(Slots, I, Z):-
	I1 is I-1,
	member(E1, Slots),
	getRest(E1, Slots, Slots1),
	selectSlotSubset(Slots1, I1, Z1),
	Z = [E1|Z1].

/*Helper to get slot selection in order*/
getRest(E, [E|T], T).

getRest(E, [_|T], Z):-
	getRest(E, T, Z).
	

oschedHelper([], [], []). 

/*oschedHelper expects the number of slots to equal the meetings*/
oschedHelper([H|T], Meetings, Z):-
	member(E2, Meetings),
	delete(Meetings, E2, Meetings2),
	oschedHelper(T, Meetings2, Z1),
	Z = [[H,E2]|Z1].

/* Part 5d */
xsched(MR,MH,C,Z):-
	osched(MR, MH, C, Z),
	noConflict(C,Z). 

noConflict([],_).

noConflict([H|T], Z):-
	H = [N,[_|_]],
	checkTimes(N, Z, []),
	noConflict(T, Z).
	
checkTimes(_,[],_).

checkTimes(U,[[[_,Time],[_,People]]|T1],Times):-
	member(U, People),
	\+member(Time, Times),
	checkTimes(U, T1, [Time|Times]).

checkTimes(U,[[_,[_,People]]|T1],Times):-
	\+member(U, People),
	checkTimes(U, T1, Times).


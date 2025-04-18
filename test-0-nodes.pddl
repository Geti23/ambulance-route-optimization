(define (problem generated-0-nodes)
(:domain ambulance world)
(:objects p0 p1 p2 p3 p4 a0 a1 h0 h1 )
(Patient(p0))
(= (Priority(p0) 1))
(Patient(p1))
(= (Priority(p1) 3))
(Patient(p2))
(= (Priority(p2) 3))
(Patient(p3))
(= (Priority(p3) 3))
(Patient(p4))
(= (Priority(p4) 1))
(Ambulance(a0))
(Ambulance(a1))
(Hospital(h0))
(Hospital(h1))
(At(p0 ))
(At(p1 ))
(At(p2 ))
(At(p3 ))
(At(p4 ))
(Waiting(p0))
(Waiting(p1))
(Waiting(p2))
(Waiting(p3))
(Waiting(p4))
(At(a0 ))
(Available(a0))
(At(a1 ))
(Available(a1))
(At(h0 ))
(At(h1 ))
)
(:goal (InHospital(p0))
(InHospital(p1))
(InHospital(p2))
(InHospital(p3))
(InHospital(p4))
))
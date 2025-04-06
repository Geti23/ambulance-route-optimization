(define (problem output-50-nodes)
(:domain ambulance world)
(:objects l0 l1 l2 l3 l4 l5 l6 l7 l8 l9 l10 l11 l12 l13 l14 l15 l16 l17 l18 l19 l20 l21 l22 l23 l24 l25 l26 l27 l28 l29 l30 l31 l32 l33 l34 l35 l36 l37 l38 l39 l40 l41 l42 l43 l44 l45 l46 l47 l48 l49 p0 p1 p2 p3 p4 a0 a1 a2 h0 h1 )
(:init (Location(l0))
(= (LocationCoord(l0) 11 5))
(= (LocationDemand(l0) 47))
(Location(l1))
(= (LocationCoord(l1) 4 3))
(= (LocationDemand(l1) 25))
(Road(l0 l1))
(= (Distance(l0 l1) 8.222394598099429))
(Location(l2))
(= (LocationCoord(l2) 13 49))
(= (LocationDemand(l2) 5))
(Road(l0 l2))
(= (Distance(l0 l2) 44.913053958365914))
(Location(l3))
(= (LocationCoord(l3) 8 23))
(= (LocationDemand(l3) 31))
(Road(l1 l3))
(= (Distance(l1 l3) 20.48231447706268))
(Location(l4))
(= (LocationCoord(l4) 26 14))
(= (LocationDemand(l4) 18))
(Road(l2 l4))
(= (Distance(l2 l4) 38.16763744851496))
(Location(l5))
(= (LocationCoord(l5) 41 48))
(= (LocationDemand(l5) 47))
(Road(l3 l5))
(= (Distance(l3 l5) 42.09283253781398))
(Location(l6))
(= (LocationCoord(l6) 36 10))
(= (LocationDemand(l6) 24))
(Road(l4 l6))
(= (Distance(l4 l6) 10.772371179080714))
(Location(l7))
(= (LocationCoord(l7) 20 18))
(= (LocationDemand(l7) 37))
(Road(l4 l7))
(= (Distance(l4 l7) 7.398810533349645))
(Location(l8))
(= (LocationCoord(l8) 3 41))
(= (LocationDemand(l8) 17))
(Road(l5 l8))
(= (Distance(l5 l8) 39.45004713945244))
(Location(l9))
(= (LocationCoord(l9) 34 19))
(= (LocationDemand(l9) 44))
(Road(l6 l9))
(= (Distance(l6 l9) 9.881990541574293))
(Location(l10))
(= (LocationCoord(l10) 20 19))
(= (LocationDemand(l10) 12))
(Road(l3 l10))
(= (Distance(l3 l10) 12.756529283166065))
(Location(l11))
(= (LocationCoord(l11) 5 41))
(= (LocationDemand(l11) 15))
(Road(l3 l11))
(= (Distance(l3 l11) 19.056447080376024))
(Location(l12))
(= (LocationCoord(l12) 10 37))
(= (LocationDemand(l12) 28))
(Road(l4 l12))
(= (Distance(l4 l12) 28.89196688146819))
(Location(l13))
(= (LocationCoord(l13) 0 43))
(= (LocationDemand(l13) 35))
(Road(l1 l13))
(= (Distance(l1 l13) 40.51882574119668))
(Location(l14))
(= (LocationCoord(l14) 17 5))
(= (LocationDemand(l14) 0))
(Road(l2 l14))
(= (Distance(l2 l14) 45.04585685538943))
(Location(l15))
(= (LocationCoord(l15) 46 32))
(= (LocationDemand(l15) 4))
(Road(l2 l15))
(= (Distance(l2 l15) 37.70485218944251))
(Location(l16))
(= (LocationCoord(l16) 42 39))
(= (LocationDemand(l16) 41))
(Road(l10 l16))
(= (Distance(l10 l16) 30.30766894652444))
(Location(l17))
(= (LocationCoord(l17) 28 6))
(= (LocationDemand(l17) 3))
(Road(l10 l17))
(= (Distance(l10 l17) 16.039937500168104))
(Location(l18))
(= (LocationCoord(l18) 48 45))
(= (LocationDemand(l18) 39))
(Road(l4 l18))
(= (Distance(l4 l18) 38.65009105522081))
(Location(l19))
(= (LocationCoord(l19) 33 36))
(= (LocationDemand(l19) 17))
(Road(l3 l19))
(= (Distance(l3 l19) 28.97271546371866))
(Location(l20))
(= (LocationCoord(l20) 6 27))
(= (LocationDemand(l20) 37))
(Road(l3 l20))
(= (Distance(l3 l20) 5.384847445047969))
(Location(l21))
(= (LocationCoord(l21) 47 21))
(= (LocationDemand(l21) 20))
(Road(l2 l21))
(= (Distance(l2 l21) 44.87604539782925))
(Location(l22))
(= (LocationCoord(l22) 41 45))
(= (LocationDemand(l22) 36))
(Road(l7 l22))
(= (Distance(l7 l22) 34.56300396028979))
(Location(l23))
(= (LocationCoord(l23) 24 10))
(= (LocationDemand(l23) 16))
(Road(l9 l23))
(= (Distance(l9 l23) 14.189461839027144))
(Location(l24))
(= (LocationCoord(l24) 25 14))
(= (LocationDemand(l24) 48))
(Road(l20 l24))
(= (Distance(l20 l24) 23.96387840650996))
(Location(l25))
(= (LocationCoord(l25) 35 49))
(= (LocationDemand(l25) 49))
(Road(l7 l25))
(= (Distance(l7 l25) 35.228492459202236))
(Location(l26))
(= (LocationCoord(l26) 28 24))
(= (LocationDemand(l26) 49))
(Road(l7 l26))
(= (Distance(l7 l26) 10.764177351691632))
(Location(l27))
(= (LocationCoord(l27) 46 27))
(= (LocationDemand(l27) 13))
(Road(l2 l27))
(= (Distance(l2 l27) 40.55373113443617))
(Location(l28))
(= (LocationCoord(l28) 47 26))
(= (LocationDemand(l28) 27))
(Road(l8 l28))
(= (Distance(l8 l28) 47.29509722248345))
(Location(l29))
(= (LocationCoord(l29) 43 42))
(= (LocationDemand(l29) 20))
(Road(l1 l29))
(= (Distance(l1 l29) 55.84821842239156))
(Location(l30))
(= (LocationCoord(l30) 25 44))
(= (LocationDemand(l30) 14))
(Road(l11 l30))
(= (Distance(l11 l30) 20.899246079590355))
(Location(l31))
(= (LocationCoord(l31) 32 47))
(= (LocationDemand(l31) 26))
(Road(l3 l31))
(= (Distance(l3 l31) 34.62953911782257))
(Location(l32))
(= (LocationCoord(l32) 40 36))
(= (LocationDemand(l32) 35))
(Road(l2 l32))
(= (Distance(l2 l32) 29.97192497627355))
(Location(l33))
(= (LocationCoord(l33) 41 7))
(= (LocationDemand(l33) 26))
(Road(l30 l33))
(= (Distance(l30 l33) 41.14096150531155))
(Location(l34))
(= (LocationCoord(l34) 24 30))
(= (LocationDemand(l34) 37))
(Road(l31 l34))
(= (Distance(l31 l34) 19.04144232619431))
(Location(l35))
(= (LocationCoord(l35) 11 8))
(= (LocationDemand(l35) 16))
(Road(l22 l35))
(= (Distance(l22 l35) 48.43290921799125))
(Location(l36))
(= (LocationCoord(l36) 45 28))
(= (LocationDemand(l36) 17))
(Road(l9 l36))
(= (Distance(l9 l36) 15.145047139542125))
(Location(l37))
(= (LocationCoord(l37) 10 49))
(= (LocationDemand(l37) 46))
(Road(l1 l37))
(= (Distance(l1 l37) 47.05283362312991))
(Location(l38))
(= (LocationCoord(l38) 11 3))
(= (LocationDemand(l38) 30))
(Road(l25 l38))
(= (Distance(l25 l38) 52.31991658932871))
(Location(l39))
(= (LocationCoord(l39) 35 5))
(= (LocationDemand(l39) 25))
(Road(l20 l39))
(= (Distance(l20 l39) 36.69206775176443))
(Location(l40))
(= (LocationCoord(l40) 47 9))
(= (LocationDemand(l40) 36))
(Road(l29 l40))
(= (Distance(l29 l40) 33.309773286823386))
(Location(l41))
(= (LocationCoord(l41) 11 0))
(= (LocationDemand(l41) 7))
(Road(l22 l41))
(= (Distance(l22 l41) 54.645499289324874))
(Location(l42))
(= (LocationCoord(l42) 31 48))
(= (LocationDemand(l42) 43))
(Road(l25 l42))
(= (Distance(l25 l42) 5.1110496964585375))
(Location(l43))
(= (LocationCoord(l43) 32 38))
(= (LocationDemand(l43) 5))
(Road(l32 l43))
(= (Distance(l32 l43) 8.338285271506834))
(Location(l44))
(= (LocationCoord(l44) 6 1))
(= (LocationDemand(l44) 22))
(Road(l13 l44))
(= (Distance(l13 l44) 42.748013281687236))
(Location(l45))
(= (LocationCoord(l45) 0 26))
(= (LocationDemand(l45) 26))
(Road(l37 l45))
(= (Distance(l37 l45) 26.024309491611834))
(Location(l46))
(= (LocationCoord(l46) 9 6))
(= (LocationDemand(l46) 16))
(Road(l44 l46))
(= (Distance(l44 l46) 6.6921837760762815))
(Location(l47))
(= (LocationCoord(l47) 19 16))
(= (LocationDemand(l47) 28))
(Road(l39 l47))
(= (Distance(l39 l47) 19.949476887615923))
(Location(l48))
(= (LocationCoord(l48) 1 49))
(= (LocationDemand(l48) 9))
(Road(l39 l48))
(= (Distance(l39 l48) 55.98601389226645))
(Location(l49))
(= (LocationCoord(l49) 0 21))
(= (LocationDemand(l49) 14))
(Road(l12 l49))
(= (Distance(l12 l49) 19.595135054474856))
(Road(l29 l35))
(= (Distance(l29 l35) 46.93479381735655))
(Road(l6 l30))
(= (Distance(l6 l30) 36.35209065479877))
(Road(l20 l46))
(= (Distance(l20 l46) 22.19868302462006))
(Road(l5 l22))
(= (Distance(l5 l22) 3.1226362078292214))
(Road(l8 l10))
(= (Distance(l8 l10) 28.549674845450916))
(Road(l19 l46))
(= (Distance(l19 l46) 38.769689027326095))
(Road(l0 l43))
(= (Distance(l0 l43) 39.29055591687387))
(Road(l3 l14))
(= (Distance(l3 l14) 20.966004221829174))
(Road(l15 l26))
(= (Distance(l15 l26) 19.96849295439057))
(Road(l8 l20))
(= (Distance(l8 l20) 15.018692001276115))
(Road(l17 l25))
(= (Distance(l17 l25) 44.50443797505112))
(Road(l19 l20))
(= (Distance(l19 l20) 29.398011002306756))
(Road(l27 l47))
(= (Distance(l27 l47) 29.83022775979586))
(Road(l30 l40))
(= (Distance(l30 l40) 41.778108870383726))
(Road(l38 l49))
(= (Distance(l38 l49) 21.354949486747437))
(Road(l21 l44))
(= (Distance(l21 l44) 46.571192431458385))
(Road(l12 l41))
(= (Distance(l12 l41) 37.469892234918234))
(Road(l20 l37))
(= (Distance(l20 l37) 23.325095401667017))
(Road(l17 l35))
(= (Distance(l17 l35) 17.401499097132312))
(Road(l29 l38))
(= (Distance(l29 l38) 50.596186275506994))
(Road(l10 l18))
(= (Distance(l10 l18) 38.34427674116566))
(Road(l5 l46))
(= (Distance(l5 l46) 53.7837675386044))
(Road(l24 l29))
(= (Distance(l24 l29) 33.778714255642484))
(Road(l17 l41))
(= (Distance(l17 l41) 18.237410406740118))
(Road(l29 l40))
(= (Distance(l29 l40) 33.63713264992581))
(Road(l0 l29))
(= (Distance(l0 l29) 49.38313245607545))
(Patient(p0))
(= (Priority(p0) 3))
(Patient(p1))
(= (Priority(p1) 3))
(Patient(p2))
(= (Priority(p2) 3))
(Patient(p3))
(= (Priority(p3) 3))
(Patient(p4))
(= (Priority(p4) 3))
(Ambulance(a0))
(Ambulance(a1))
(Ambulance(a2))
(Hospital(h0))
(Hospital(h1))
(At(p0 l25))
(At(p1 l26))
(At(p2 l24))
(At(p3 l0))
(At(p4 l5))
(Waiting(p0))
(Waiting(p1))
(Waiting(p2))
(Waiting(p3))
(Waiting(p4))
(At(a0 l37))
(Available(a0))
(At(a1 l29))
(Available(a1))
(At(a2 l14))
(Available(a2))
(At(h0 l48))
(At(h1 l11))
)
(:goal (InHospital(p0))
(InHospital(p1))
(InHospital(p2))
(InHospital(p3))
(InHospital(p4))
))
{0} ssp 1;
{1} mst 0;
{2} cup 0 27;
{3} stp;
{4} ldc 0;
{5} str 0 0;
{6} retf;
{7} ssp 6;
{8} ldc 1;
{9} str 0 0;
{10} retf;
{11} ssp 6;
{12} ssp 7;
{13} ldc 2;
{14} str 0 0;
{15} retf;
{16} ssp 6;
{17} lda 0 5;
{18} ind;
{19} fjp 24;
{20} ldc 100;
{21} str 0 0;
{22} retf;
{23} ujp 27;
{24} ldc 200;
{25} str 0 0;
{26} retf;
{27} ssp 6;
{28} ssp 7;
{29} ssp 8;
{30} ssp 9;
{31} lda 0 5;
{32} dpl;
{33} mst 1;
{34} cup 0 4;
{35} sto;
{36} ind;
{37} ssp 9;
{38} lda 0 6;
{39} dpl;
{40} mst 1;
{41} lda 0 5;
{42} movs 1;
{43} cup 1 7;
{44} sto;
{45} ind;
{46} ssp 9;
{47} lda 0 7;
{48} dpl;
{49} mst 1;
{50} ldc 2;
{51} ldc 3;
{52} cup 2 11;
{53} sto;
{54} ind;
{55} ssp 9;
{56} lda 0 8;
{57} dpl;
{58} mst 1;
{59} ldc true;
{60} cup 1 16;
{61} sto;
{62} ind;
{63} ssp 9;
{64} retp;

{0} ssp 1;
{1} mst 0;
{2} cup 0 30;
{3} stp;
{4} ssp 6;
{5} lda 0 5;
{6} ind;
{7} ldc 0;
{8} equ;
{9} lda 0 5;
{10} ind;
{11} ldc 1;
{12} equ;
{13} or;
{14} fjp 19;
{15} ldc 1;
{16} str 0 0;
{17} retf;
{18} ujp 30;
{19} lda 0 5;
{20} ind;
{21} mst 1;
{22} lda 0 5;
{23} ind;
{24} ldc 1;
{25} sub;
{26} cup 1 4;
{27} mul;
{28} str 0 0;
{29} retf;
{30} mst 1;
{31} ldc 5;
{32} cup 1 4;
{33} retp;

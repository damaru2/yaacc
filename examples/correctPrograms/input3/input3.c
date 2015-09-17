void main(){
	int i =5, j, k, l;

	while (i--){
		if (i == 1){//aquí se hace break, la i acabará valiendo 1.
			l= 30;
			break;
		}
		if (i == 2){
			k = 17;
			continue;
		}
		j = i; //la j valdrá 3 pues cuando i==2 se hace continue y en i==1 se hace break

	}
	// Se espera i = 1, j=3, k = 17, l=30
}

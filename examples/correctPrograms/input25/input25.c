void quicksort(int, int);

int x[20];

void main(){
  int size=20,i;

  for(i=0;i<size;i++)
	  x[i]=(i*7)%20;

  quicksort(0,size-1);
}

void quicksort(int first,int last){
    int pivot,j,temp,i;

     if(first<last){
         pivot=first;
         i=first;
         j=last;

         while(i<j){
             while(x[i]<=x[pivot]&&i<last)
                 i++;
             while(x[j]>x[pivot])
                 j--;
             if(i<j){
                 temp=x[i];
                  x[i]=x[j];
                  x[j]=temp;
             }
         }

         temp=x[pivot];
         x[pivot]=x[j];
         x[j]=temp;
         quicksort(first,j-1);
         quicksort(j+1,last);

    }
}

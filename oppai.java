
public class oppai {

	public static void main(String[] args){
		for(int i = 0; i < 24; i++){
			System.out.println(oppai.chooseNumOrder(i,10.0));
		}
	}
	public static double opp(double n1, double n2, double n3, double n4, double targetnum){
		for(int i1 = 0; i1 < 4; i1++){
			for(int i2 = 0; i2 < 4; i2++){
				for(int i3 = 0; i3 < 4; i3++){
					if(targetnum == Math.abs(any(any(any(n1,n2,i1),n3,i2),n4,i3)) || 1/targetnum == Math.abs(any(any(any(n1,n2,i1),n3,i2),n4,i3))) return i1*100+i2*10+i3 + 0.1;
					if(targetnum == Math.abs(any(any(n1,n2,i1), any(n3,n4,i2),i3 )) || 1/targetnum == Math.abs(any(any(n1,n2,i1), any(n3,n4,i2),i3 )) ) return i1*100+i2*10+i3 + 0.2;
				}
			}
		}
		return 0;
	}
	
	public static double chooseNumOrder(int num, double targetnum){
		double n1 = 8.0, n2 =5.0, n3 = 1.0, n4 =1.0;
		if(num ==0) return num*1000 + opp(n1, n2, n3, n4, targetnum);
		if(num ==1) return num*1000 + opp(n1, n2, n4, n3, targetnum);
		if(num ==2) return num*1000 + opp(n1, n3, n2, n4, targetnum);
		if(num ==3) return num*1000 + opp(n1, n3, n4, n2, targetnum);
		if(num ==4) return num*1000 + opp(n1, n4, n3, n2, targetnum);
		if(num ==5) return num*1000 + opp(n1, n4, n2, n3, targetnum);
		
		if(num ==6) return num*1000 + opp(n2, n1, n3, n4, targetnum);
		if(num ==7) return num*1000 + opp(n2, n1, n4, n3, targetnum);
		if(num ==8) return num*1000 + opp(n2, n3, n1, n4, targetnum);
		if(num ==9) return num*1000 + opp(n2, n3, n4, n1, targetnum);
		if(num ==10) return num*1000 + opp(n2, n4, n3, n1, targetnum);
		if(num ==11) return num*1000 + opp(n2, n4, n1, n3, targetnum);
		
		if(num ==12) return num*1000 + opp(n3, n2, n4, n1, targetnum);
		if(num ==13) return num*1000 + opp(n3, n2, n1, n4, targetnum);
		if(num ==14) return num*1000 + opp(n3, n4, n2, n1, targetnum);
		if(num ==15) return num*1000 + opp(n3, n4, n1, n2, targetnum);
		if(num ==16) return num*1000 + opp(n3, n1, n4, n2, targetnum);
		if(num ==17) return num*1000 + opp(n3, n1, n2, n4, targetnum);
		
		if(num ==18) return num*1000 + opp(n4, n2, n3, n1, targetnum);
		if(num ==19) return num*1000 + opp(n4, n2, n1, n3, targetnum);
		if(num ==20) return num*1000 + opp(n4, n1, n3, n2, targetnum);
		if(num ==21) return num*1000 + opp(n4, n1, n2, n3, targetnum);
		if(num ==22) return num*1000 + opp(n4, n3, n2, n1, targetnum);
		if(num ==23) return num*1000 + opp(n4, n3, n1, n2, targetnum);
		return -1;
	}
	
	public static double any(double n1, double n2, int opp){
		try{
			if(opp == 0) return n1*n2;
			if(opp == 1) return n1/n2;
			if(opp == 2) return n1+n2;
			if(opp == 3) return n1-n2;
		}catch(Exception e){
		}
		return -1.0;
	}
}


public class Package {
	
	private String packageName;
	private String activityUnit;
	private String nuclideName;
	private int lld;
	private double activity;
	
	public Package(){}
	
	
	public Package(String pName, String aUnit, String n,int l, double a){
		packageName = pName;
		activityUnit = aUnit;
		nuclideName = n;
		lld = l;
		activity = a;
	}
	
	//getters
	public String getPackageName(){
		return packageName;
		
	}
	public String getActivityUnit(){
		return activityUnit;
		
	}
	
	public String getNuclideName(){
		return nuclideName;
		
	}
	public int getLLD(){
		return lld;
		
	}
	public double getActivity(){
		return activity;
		
	}
	// setters
	public void setPackageName(String p){
		
		packageName = p;
	}
	 public void setActivityUnit(String aUnit){
			
		activityUnit = aUnit;
		}
	public void setNuclideName(String n){
		
		nuclideName = n;
	}
    public void setLLD(int l){
		
		lld = l;
	}
    public void setActivity(double a){
		
		activity = a;
	}
   

    public String toString(){
    	return packageName +" "+ activityUnit +" "+ nuclideName +" "+ lld +" "+ activity;
    }
}

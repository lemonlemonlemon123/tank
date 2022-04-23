//package tank1;
//
//public class IsDisappear {
//    private int direct1;
//    private boolean isAction=false;
//    private int x1;
//    private int y1;
//    private int x2;
//    private int y2;
//    public void userB(int direct1,int x1,int y1){
//        this.direct1=direct1;
//        this.x1=x1;
//        this.y1=y1;
//    }
//    public void O1(int x2,int y2){
//        this.x2=x2;
//        this.y2=y2;
//    }
//    public boolean isDisappear(){
//        switch(direct1){
//            case 1:if((y1+30>=y2&&y1+30<=y2+60)&&(x1>=x2&&x1<=x2+50)){
//                return true;
//            }
//            else return false;
//            case 2:if((y1>=y2+20&&y1<=y2+80)&&(x1>=x2&&x1<=x2+50)){
//                return true;
//            }
//            else return false;
//            case 3:if((x1+30>=x2&&x1+30<=x2+60)&&(y1>=y2&&y1<=y2+50)){
//                return true;
//            }
//            else return false;
//            case 4:if((x1>=x2+20&&x1<=x2+80)&&(y1>=y2&&y1<=y2+50)){
//                return true;
//            }
//            else return false;
//        }
//        return false;
//    }
//    public boolean isAction() {
//        return isAction;
//    }
//    public void setAction(boolean action) {
//        isAction = action;
//    }
//}

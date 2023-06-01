package com.eureka.base.hy;

/**
 * @program: itheima20210917_StudyTest
 * @description: 心型图案
 * @author: Mr.Huang
 * @create: 2022-01-17 14:20
 **/
public class CardioidDemo {
    public static void main(String[] args){
        //new Cardioid(); // ❤型图案
        //heartTwo(15,0.9,"爱","我","宝宝");
        heartTwoWithXK(15,1.0,"爱","我","宝宝");
    }
    private static void heartTwo(int r,double size,String center,String left,String right){
        size=1/(1.5*r*size);
        StringBuilder sb=new StringBuilder();
        for (int y = r; y > -r; y--,sb.append("\n"))
            for (int x = -2*r; x <4*r; x++ ) {
                boolean isLeft=inHeart(size,x,y);
                boolean isRight=inHeart(size,x-25,y-3);
                //双空格
                String req=null;
                if(isLeft && isRight) req=center;
                else if(isLeft) req=left;
                else if (isRight) req=right;
                if(req!=null) sb.append((req + req).charAt((x - y) % req.length() + req.length()));
                else sb.append(" ");//双空格
            }
        System.err.println(sb.toString());
    }
    private static void heartTwoWithXK(int r,double size,String center,String left,String right){
        size=1/(1.5*r*size);
        StringBuilder sb=new StringBuilder();
        for (int y = r; y >=-r; y--,sb.append("\n"))
            for (int x = -2*r; x <= 4*r; x++ ) {
                boolean isLeft=inHeart(size,x,y+3);
                boolean isRight=inHeart(size,x-25,y);
                //双空格
                String req=null;
                String w="";
                if(isLeft && isRight) req=center;
                else if(isLeft) req=left;
                else if (isRight) req=right;
                else if((y==-r || y==r)) {
                    if (x < 3 * r - 7) {
                        req = "♥";
                        w = " ";
                    }
                }
                else if(x==4*r || x==-2*r || line(x,y+3)) req="♥";
                if(req!=null) sb.append((req + req).charAt((x - y) % req.length() + req.length()) + w);
                else sb.append(" ");//双空格
            }
        System.err.println(sb.toString());
    }
    private static boolean inHeart(double size,int x,int y){
        return Math.pow(Math.pow(x * size, 2) +
                Math.pow(y * 2*size, 2) - 1, 3) - Math.pow(x * size, 2) * Math.pow(y * 2*size, 3) <= 0;

    }
    private static boolean line(int x,int y){
        return 4*y-x == 0;
    }
}

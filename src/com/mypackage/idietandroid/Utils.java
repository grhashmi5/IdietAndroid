package com.mypackage.idietandroid;

 public class Utils
 {
   public static double round(double Rval, int Rpl)
   {
     double p = Math.pow(10.0D, Rpl);
     Rval *= p;
     double tmp = Math.round(Rval);
     return tmp / p;
   }
 
   public static double percent(double Pval, double Pct)
   {
     return Pval * Pct / 100.0D;
   }
 
   public static double npercent(double Pval, double Ppart)
   {
     return 100.0D * Ppart / Pval;
   }
 
   public static double ppercent(double Ppart, double Pct)
   {
     return 100.0D * Ppart / Pct;
     
   }
}

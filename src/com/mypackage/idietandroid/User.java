/*     */ package com.mypackage.idietandroid;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class User
/*     */   
/*     */ {
/*     */   private static final long serialVersionUID = 761439066L;
/*  19 */   String firstName = "";
/*  20 */   String lastName = "";
/*  21 */   String pwd = "";
/*  22 */   double height = 0.0D;
/*  23 */   double weight = 0.0D;
/*     */   int bPressure;
/*     */   int bSugar;
/*     */   int metabolism;
/*     */   int activity;
/*     */   int byear;
/*     */   int bday;
/*     */   int bmonth;
/*     */   int gender;
/*  25 */   HashMap days = new HashMap();
/*  26 */   HashMap day = new HashMap();
/*  27 */   int units = 0;
/*  28 */   /*TTModel model = new TTModel();
  29    TTModel custom_m = new TTModel();
  30    FoodNode customFoodRoot = new FoodNode("db", 1, 1);*/
/*     */ 
/*  32 */   int cfcount = 100;
/*     */   String date;
/*     */   int age;
/*  35 */   double totalCals = 0.0D;
/*  36 */   double carbs = 0.0D;
/*  37 */   double carbsp = 0.0D;
/*  38 */   double fat = 0.0D;
/*  39 */   double fatp = 0.0D;
/*  40 */   double protein = 0.0D;
/*  41 */   double proteinp = 0.0D;
/*  42 */   double scals = 0.0D;
/*  43 */   int diettype = 0;
/*  44 */   int water = 0;
/*  45 */   int cusomfoods_number = 0;
/*     */ 
/*     */   public int getCusomfoods_number() {
/*  48 */     return this.cusomfoods_number;
/*     */   }
/*     */ 
/*     */   public void setCusomfoods_number(int cusomfoods_number) {
/*  52 */     this.cusomfoods_number = cusomfoods_number;
/*     */   }
/*     */ 
/*        public FoodNode getCustomFoodRoot() {
  56      return this.customFoodRoot;
        }
      
        public void setCustomFoodRoot(FoodNode customFoodRoot) {
  60      this.customFoodRoot = customFoodRoot;
        }*/
/*     */ 
/*     */   public HashMap getDays()
/*     */   {
/* 132 */     return this.days;
/*     */   }
/*     */ 
/*     */   public void setDays(HashMap days) {
/* 136 */     this.days = days;
/*     */   }
/*     */ 
/*     */   public String getNames() {
/* 140 */     String file = this.firstName.replaceAll(" ", "_") + "_" + this.lastName.replaceAll(" ", "_");
/* 141 */     return file;
/*     */   }
/*     */ 
/*        public TTModel getCustom() {
 145      return this.custom_m;
        }
*//*     */   public User() {
/*     */   }
/*     */ 
/*     */   public void setDiet(String date) {
/* 151 */     HashMap cday = (HashMap)this.days.get(date);
/* 152 */     cday.put("calories", Double.valueOf(this.totalCals));
/* 153 */     cday.put("fat", Double.valueOf(this.fat));
/* 154 */     cday.put("protein", Double.valueOf(this.protein));
/* 155 */     cday.put("carbs", Double.valueOf(this.carbs));
/* 156 */     cday.put("carbsp", Double.valueOf(this.carbsp));
/* 157 */     cday.put("fatp", Double.valueOf(this.fatp));
/* 158 */     cday.put("proteinp", Double.valueOf(this.proteinp));
/* 159 */     this.days.put(date, cday);
/*     */   }


/*@Override
  public int describeContents(){
         return 0;
     }
  
     @Override
    public void writeToParcel(Parcel dest, int flags) {
    	 dest.writeArray(this.u);
         dest.writeStringArray(new String[] {this.firstName,
                                             this.lastName,
                                             this.pwd,
                                             this.date}                                             
                                             );
         dest
     }
     
     public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public Student createFromParcel(Parcel in) {
             return new Student(in); 
         }
  
         public Student[] newArray(int size) {
             return new Student[size];
         }
     };*/

/*     */ 
/*     */   public void getDiet(String date) {
/* 163 */     HashMap cday = (HashMap)this.days.get(date);
/* 164 */     this.totalCals = new Double(cday.get("calories").toString()).doubleValue();
/* 165 */     this.fat = new Double(cday.get("fat").toString()).doubleValue();
/* 166 */     this.protein = new Double(cday.get("protein").toString()).doubleValue();
/* 167 */     this.carbs = new Double(cday.get("carbs").toString()).doubleValue();
/* 168 */     this.carbsp = new Double(cday.get("carbsp").toString()).doubleValue();
/* 169 */     this.fatp = new Double(cday.get("fatp").toString()).doubleValue();
/* 170 */     this.proteinp = new Double(cday.get("proteinp").toString()).doubleValue();
/*     */   }
/*     */   public User(String firstName, String lastName, double height, double weight, int bPressure, int bSugar, int metabolism, int activity, int bday, int bmonth, int byear, int gender, int units) {
/* 173 */     this.firstName = firstName;
/* 174 */     this.lastName = lastName;
/* 175 */     this.height = height;
/* 176 */     this.weight = weight;
/* 177 */     this.bPressure = bPressure;
/* 178 */     this.bSugar = bSugar;
/* 179 */     this.activity = activity;
/* 180 */     this.bday = bday;
/* 181 */     this.byear = byear;
/* 182 */     this.bmonth = bmonth;
/* 183 */     this.gender = gender;
/* 184 */     this.metabolism = metabolism;
/* 185 */     this.units = units;
/* 186 */     Calendar now = Calendar.getInstance();
/* 187 */     Calendar bd = Calendar.getInstance();
/* 188 */     bd.set(byear, bmonth, bday);
/* 189 */     this.age = (now.get(1) - bd.get(1));
/* 190 */     bd.add(1, this.age);
/* 191 */     if (now.before(bd)) {
/* 192 */       this.age -= 1;
/*     */     }
/* 194      this.model = new TTModel();*/
/* 195 */     this.date = new String(new SimpleDateFormat("dd MMMMM yyyy").format(Calendar.getInstance().getTime()));
/* 196      this.custom_m = new TTModel();*/
/* 197      this.day.put("model", this.model);*/
/* 198 */     this.day.put("calories", Double.valueOf(this.totalCals));
/* 199 */     this.day.put("fat", Double.valueOf(this.fat));
/* 200 */     this.day.put("protein", Double.valueOf(this.protein));
/* 201 */     this.day.put("carbs", Double.valueOf(this.carbs));
/* 202 */     this.day.put("carbsp", Double.valueOf(this.carbsp));
/* 203 */     this.day.put("fatp", Double.valueOf(this.fatp));
/* 204 */     this.day.put("proteinp", Double.valueOf(this.proteinp));
/* 205 */     this.days.put(this.date, this.day);
/*     */   }
/*     */ }

/* Location:           C:\Users\GHULAMRASOOL\Desktop\iDiet-1.0.5\iDiet-1.0.5\iDiet.jar
 * Qualified Name:     idiet.User
 * JD-Core Version:    0.6.0
 */
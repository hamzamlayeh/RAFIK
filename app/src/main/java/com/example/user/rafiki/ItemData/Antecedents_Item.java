package com.example.user.rafiki.ItemData;

public class Antecedents_Item {
   private int _id;
   private String acte;
   private String date;

   public Antecedents_Item(String acte, String date) {
      this.acte = acte;
      this.date = date;
   }

   public int get_id() {
      return _id;
   }

   public void set_id(int _id) {
      this._id = _id;
   }

   public String getActe() {
      return acte;
   }

   public void setActe(String acte) {
      this.acte = acte;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }
}

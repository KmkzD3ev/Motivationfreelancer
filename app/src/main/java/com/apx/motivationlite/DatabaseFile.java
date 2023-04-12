package com.apx.motivationlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.Preference;

import com.apx.motivationlite.Model.CollectionModel;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class DatabaseFile extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MotivationLite";

    private static final  String LikedQuotes = "LikedQuotes";

    private static final String Collections = "Collections";

    private static final String CollectionItem = "CollectionItem";

    private static final String Quotes = "Quotes";

    private static final String History = "History";

    private static final String OwnQuotes = "OwnQuotes";

    private static final String ForbiddenWord = "ForbiddenWord";

    private static final String id = "id";

    private static final String quotes = "quotes";

    private static final String author = "author";

    private static final String category = "category";

    private static final String language = "language";

    private static final String name = "name";

    private static final String CollectionId = "CollectionId";

    private static final String date = "date";

    private static final String word = "word";

    private static final String preference ="preference";

    public DatabaseFile (Context context) { super(context,DATABASE_NAME,null,3);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ LikedQuotes +" ("+ id + " TEXT, " + quotes + " TEXT, " + author + " TEXT, " + category + " TEXT, " + language + " TEXT)");
        db.execSQL("CREATE TABLE "+ History +" ("+ id + " TEXT, " + quotes + " TEXT, " + author + " TEXT, " + category + " TEXT, " + language + " TEXT)");
        db.execSQL("CREATE TABLE "+ Quotes +" ("+ id + " TEXT, " + quotes + " TEXT, " + author + " TEXT, " + category + " TEXT, " + language + " TEXT)");
        db.execSQL("CREATE TABLE "+ CollectionItem +" ("+ id + " TEXT, " + quotes + " TEXT, " + author + " TEXT, " + category + " TEXT, " + language + " TEXT, " + CollectionId + " TEXT)");

        db.execSQL("CREATE TABLE "+ Collections +" ("+ id + " TEXT, " + quotes + " TEXT)");
        db.execSQL("CREATE TABLE "+ OwnQuotes +" ("+ id + " TEXT, " + name + " TEXT )");
        db.execSQL("CREATE TABLE "+ ForbiddenWord +" ("+ id + " TEXT, " + word + " TEXT  )");
        db.execSQL("CREATE TABLE " + preference +" (name TEXT)");

    }

    public void AddLiked (String Id,String Quotes,String Author,String Category,String Language){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id,Id);
        values.put(quotes,Quotes);
        values.put(author,Author);
        values.put(category,Category);
        values.put(language,Language);
        db.insert(LikedQuotes, null,values);
                db.close();
    }

    public void Addhistory (String Id,String Quotes,String Author,String Category,String Language) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id,Id);
        values.put(quotes,Quotes);
        values.put(author,Author);
        values.put(category,Category);
        values.put(language,Language);
        db.insert(History, null,values);
        db.close();
    }

    public void AddCollectionItem (String Id,String Quotes,String Author,String Category,String Language,String collectionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id,Id);
        values.put(quotes,Quotes);
        values.put(author,Author);
        values.put(category,Category);
        values.put(language,Language);
        values.put(CollectionId,collectionId);
        db.insert(CollectionItem, null,values);
        db.close();
    }

    public void AddCollections (String Id, String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, Id);
        values.put(quotes, Name);
        db.insert(Collections, null,values);
        db.close();
    }
    public void AddOwnQuotes (String Id, String Quotes,String Date ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id,Id);
        values.put(name,Quotes);
        db.insert(OwnQuotes, null,values);
        db.close();
    }
    public void AddForbiddenWord (String Id, String Word ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id,Id);
        values.put(word,Word);
        db.insert(ForbiddenWord, null,values);
        db.close();
    }

    public void updateLiked  (String Id,String Quotes,String Author,String Category,String Language) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(quotes,Quotes);
        values.put(author,Author);
        values.put(category,Category);
        values.put(language,Language);
        db.update(LikedQuotes,values,id+"=?",new String[]{Id});
       db.close();
    }
    public void updatehistoy  (String Id,String Quotes,String Author,String Category,String Language) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(quotes,Quotes);
        values.put(author,Author);
        values.put(category,Category);
        values.put(language,Language);
        db.update(History,values,id+"=?",new String[]{Id});
       db.close();
    }
    public void updateCollectionItem  (String Id,String Quotes,String Author,String Category,String Language) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(quotes,Quotes);
        values.put(author,Author);
        values.put(category,Category);
        values.put(language,Language);
        db.update(CollectionItem,values,id+"=?",new String[]{Id});
       db.close();
    }
    public void updateOwnQuotes (String Id, String Quotes,String Date ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(quotes,Quotes);
        values.put(date,Date);
        db.update(OwnQuotes, values,id+"=?",new String[] {Id});
        db.close();
    }
    public void updateCollections (String Id, String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name,Name);
        db.update (Collections,values,id+"=?",new String[]{Id});
        db.close();
    }
    public void updateForbiddenWord (String Id, String Word ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(word,Word);
        db.update (ForbiddenWord,values,id+"=?",new String[]{Id});
        db.close();
    }

    public void AddPreference (String as){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name,as);
        db.insert(preference , null,values);
        db.close();
    }
    public void deleteLiked  (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LikedQuotes,id + "=?", new String[]{Id});
        db.close();
    }
    public void deletehistoy  (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(History,id+"=?",new String[]{Id});
        db.close();
    }
    public void deleteCollectionItem (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CollectionItem,id+"=?",new String[]{Id});
        db.close();

    }
    public void deleteOwnQuotes  (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(OwnQuotes,id+"=?",new String[]{Id});
        db.close();

    }
    public void deleteCollections  (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Collections,id+"=?",new String[]{Id});
        db.close();

    }
    public void deleteForbiddenWord  (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ForbiddenWord,id+"=?",new String[]{Id});
        db.close();

    }

    public void deletePreference  (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(preference,name+"=?",new String[]{Id});
        db.close();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LikedQuotes);
        db.execSQL("DROP TABLE IF EXISTS " + History);
        db.execSQL("DROP TABLE IF EXISTS " + Quotes);
        db.execSQL("DROP TABLE IF EXISTS " + CollectionItem);
        db.execSQL("DROP TABLE IF EXISTS " + Collections);
        db.execSQL("DROP TABLE IF EXISTS " + OwnQuotes);
        db.execSQL("DROP TABLE IF EXISTS " + ForbiddenWord);
        db.execSQL("DROP TABLE IF EXISTS " + preference);

        onCreate(db);
    }

    public ArrayList<MainModel> getHistory (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + History,null);
        ArrayList<MainModel> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(new MainModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(4),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }
    public ArrayList<MainModel> getLiked (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + LikedQuotes,null);
        ArrayList<MainModel> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(new MainModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(4),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }
    public ArrayList<String> getlike (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + LikedQuotes,null);
        ArrayList<String> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(
                        cursor.getString(0).toString()

                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }
    public ArrayList<CollectionModel> getcollection () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Collections,null);
                ArrayList<CollectionModel> List = new ArrayList<>();
                if (cursor.moveToFirst()){
                    do {
                        List.add(new CollectionModel(
                                cursor.getString(0).toString(),
                                cursor.getString(1).toString()
                                )

                        );
                    }while(cursor.moveToNext());
                }
                cursor.close();
                db.close();
                return List;
    }

    public ArrayList<String> getcollections (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CollectionItem,null);
        ArrayList<String> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(
                        cursor.getString(0).toString()

                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }

    public ArrayList<String> gethistorytable (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + History,null);
        ArrayList<String> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(
                        cursor.getString(0).toString()

                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }

    public ArrayList<OwnModel> getown () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + OwnQuotes,null);
        ArrayList<OwnModel> List = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                List.add(new OwnModel(
                                cursor.getString(0).toString(),
                                cursor.getString(1).toString()

                        )

                );
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;
    }

    public ArrayList<MainModel> getcollectionitem  (String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CollectionItem +  " WHERE " + CollectionId + "=?",new String[]{id});
        ArrayList<MainModel> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(new MainModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(4),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }
    public ArrayList<String> getpreference (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + preference,null);
        ArrayList<String> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(
                        cursor.getString(0).toString()

                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }

    public ArrayList<ForbiddenModel> getForbidden  (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ForbiddenWord,null);
        ArrayList<ForbiddenModel> List = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                List.add(new ForbiddenModel(
                        cursor.getString(0),
                        cursor.getString(1)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return List;

    }





}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent;

import database.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class entBarang {
    private String atKdBrg = "";
    public String atNamaBrg = "";
    public String atSatuan = "";
    public int atHarga;
    public int atStok;
    
    public void setKdBrg(String pKdBrg){
        this.atKdBrg = pKdBrg;
    }
    
    public String getKdBrg(){
        return this.atKdBrg;
    }
    
    public void cariBarang(){
        String vSql =   "SELECT barang.KdBrg, barang.NamaBrg, barang.Satuan, barang.Harga, barang.Stok " +
                        "FROM `barang` WHERE barang.KdBrg = '"+ this.atKdBrg +"'";
        
        dbConnection db = new dbConnection();
        Connection con = db.koneksiDB();
        PreparedStatement stat = null;
        try{
            stat = con.prepareStatement(vSql);
            ResultSet rs = stat.executeQuery();
            if(rs.next()){
                this.atKdBrg = rs.getString("KdBrg");
                this.atNamaBrg = rs.getString("NamaBrg");
                this.atSatuan = rs.getString("Satuan");
                this.atHarga = Integer.valueOf(rs.getString("Harga"));
                this.atStok = Integer.valueOf(rs.getString("Stok"));
            }else{
                this.atNamaBrg = "";
                this.atSatuan = "";
                this.atHarga = 0;
                this.atStok = 0;
            }
                      
        } catch (SQLException e){
            System.out.println("error----->"+e.toString());
        } 
        
        
    }
    
    public void insertBarang() {
       
       String vSql =    "INSERT INTO barang " +
                        "(barang.KdBrg, barang.NamaBrg, barang.Satuan, barang.Harga, barang.Stok) " +
                        "VALUES ('"+ this.atKdBrg +"', '"+ this.atNamaBrg +"', '"+ this.atSatuan +"', "+ this.atHarga +", "+ this.atStok +")";
       
        dbConnection db = new dbConnection();
        Connection con = db.koneksiDB();
        PreparedStatement Stat = null;
        try{
            Stat = con.prepareStatement(vSql);           
            Stat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error -> "+ex.toString());
        }
    } 
    
    public void updateBarang() {
        String vSql =   "UPDATE barang SET " +
                        "barang.NamaBrg = '"+ this.atNamaBrg +"', barang.Satuan = '"+ this.atSatuan +"', " +
                        "barang.Harga = "+ this.atHarga +", barang.Stok = "+ this.atStok +" " +
                        "WHERE barang.KdBrg = '"+ this.atKdBrg +"'";
        dbConnection db = new dbConnection();
        Connection con = db.koneksiDB();
        PreparedStatement Stat = null;
        try{
            Stat = con.prepareStatement(vSql);                                                
            Stat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error -> "+ex.toString());
        }
   }
    
    public void deleteBarang() {
       String vSql="DELETE FROM barang WHERE barang.KdBrg='"+this.atKdBrg+"'";
       dbConnection db = new dbConnection();
        Connection con = db.koneksiDB();
        PreparedStatement Stat = null;
        try{
            Stat = con.prepareStatement(vSql);                                                
            Stat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error -> "+ex.toString());
        }
   }
    
    public static void main(String[] args){
        entBarang o = new entBarang();
        
        // [DELETE]
        // o.setKdBrg("003");
        // o.deleteBarang();
        
        
        // [UPDATE]
        // o.setKdBrg("004");
        // o.atNamaBrg="Janjiw";
        // o.atSatuan="Cup";
        // o.atHarga= 11111;
        // o.atStok= 2;
        // o.updateBarang();
        
        

        // [INSERT]
        // o.setKdBrg("004");
        // o.atNamaBrg="Kopi ABC";
        // o.atSatuan="Sachet";
        // o.atHarga = 3300;
        // o.atStok = 10;
        // o.insertBarang();

        // [SEARCH]
        // o.setKdBrg("003");
        // o.cariBarang();
        // System.out.println("Kd Brg : "+o.getKdBrg());
        // System.out.println("Nama Brg : "+o.atNamaBrg);
        // System.out.println("Satuan : "+o.atSatuan);
        // System.out.println("Harga : "+o.atHarga);
        // System.out.println("Stok : "+o.atStok);
    }
}

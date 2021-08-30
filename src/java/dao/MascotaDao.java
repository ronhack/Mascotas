package dao;

import conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import modelo.Mascota;

public class MascotaDao {
    Conexion conn;
    
    public MascotaDao(Conexion conn){
        this.conn = conn;
    }
    
    //Método para guardar en la BD
    public boolean insertar(Mascota pet){
        String sql = "insert into mascota values(?,?,?,?,?)";
        try{
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            java.sql.Date sqlDate = convert(pet.getFecha_nacimiento());
            
            ps.setInt(1, pet.getId());
            ps.setString(2, pet.getNombre());
            ps.setDate(3,  sqlDate);
            ps.setString(4, pet.getCategoria());
            ps.setString(5, pet.getColor());
            ps.executeUpdate();
            
            return true;
        }catch(Exception e){
            return false;
        }
    }   

    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    //Método para consultar en la BD
    public List<Mascota> selectAll(){
        String sql = "select * from mascota";
        try{
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            List<Mascota> lista = new LinkedList<>();
            Mascota pet;
            while(rs.next()){
                pet = new Mascota(rs.getInt("id"));
                pet.setNombre(rs.getString("nombre"));
                pet.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                pet.setCategoria(rs.getString("categoria"));
                pet.setColor(rs.getString("color"));
                lista.add(pet);
            }
            return lista;
        }catch(Exception e){
            return null;
        }
    }
}

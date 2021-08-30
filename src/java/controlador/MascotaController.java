package controlador;

import conexion.Conexion;
import dao.MascotaDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import modelo.Mascota;

public class MascotaController {

    public static void main(String[] args) throws ParseException {
        Scanner scan = new Scanner(System.in);
        Mascota pet = new Mascota(0);
        Conexion conn = new Conexion();
        MascotaDao petd = new MascotaDao(conn);

        //Solicitamos los datos a insertar
        System.out.println("\nDatos de la mascota");
        System.out.println("--------------------------------------");
        
        System.out.println("Ingrese el nombre de la mascota: ");
        pet.setNombre(scan.next());
        System.out.println("Ingrese la fecha de nacimiento YYYY-MM-DD: ");
        String date1 = scan.next();
        Date datef = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        pet.setFecha_nacimiento(datef);
        
        //Validando formato de fecha
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = null;
        String date = date1;
        try {
            testDate = df.parse(date);
        } catch (Exception e) {
            System.out.println("Formato no válido");
        }
        if (!df.format(testDate).equals(date)) {
            System.out.println("Fecha inválida!");
        }
        //Fin validacion formato de fecha
        
        System.out.println("Ingrese la categoría: ");
        pet.setCategoria(scan.next());
        System.out.println("Ingrese el color: ");
        pet.setColor(scan.next());
        //Hacemos la inserción en la BD
        boolean respuesta = petd.insertar(pet);
        //Válidamos si se hizo la inserción de los datos
        if (respuesta) {
            System.out.println("\n¡Registro guardado!\n");
        } else {
            System.out.println("\n¡Error, registro no guardado!\n");
        }

        // Recorremos los registros de la tabla mascota y los mostramos
        for (Mascota pet1 : petd.selectAll()) {
            System.out.println("ID: " + pet1.getId());
            System.out.println("Nombre de la mascota: " + pet1.getNombre());
            System.out.println("Fecha de nacimiento: " + pet1.getFecha_nacimiento());
            System.out.println("Categoría: " + pet1.getCategoria());
            System.out.println("Color: " + pet1.getColor());
            System.out.println("--------------------------------\n");
        }
    }

}

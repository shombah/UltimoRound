package ObjetosDB;


public class Usuarios extends metodosDB
{
    private int id_usuario;
    private String nombre;
    private String login;
    private String password;
    
    public Usuarios(Integer idUsuario, String nombre, String login, String password) { //Constructor
        this.id_usuario = idUsuario;
        this.nombre = nombre;
        this.login = login;
        this.password = password;
    }
    /*Setters y Getters*/
    public Integer getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.id_usuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*Fin Setters y Getters*/
    
    
}

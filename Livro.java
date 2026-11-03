public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true; // Por padrão, o livro começa disponível
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }

    public void emprestar() {
        this.disponivel = false;
    }

    public void devolver() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        String status = disponivel ? "🟢 Disponível" : "🔴 Emprestado";
        return titulo + " - " + autor + " [" + status + "]";
    }
}

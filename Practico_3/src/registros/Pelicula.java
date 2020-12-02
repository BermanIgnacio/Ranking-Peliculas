package registros;

public class Pelicula implements Comparable<Pelicula> {

	private int movieId;
	private String titulo;
	private Integer viewers;
	private Double ranking;

	private Integer rank0;
	private Integer rank1;
	private Integer rank2;
	private Integer rank3;
	private Integer rank4;
	private Integer rank5;
	
	public Pelicula() {
		
		this.viewers = 0;
		this.ranking = 0.0;
		this.rank0 = 0;
		this.rank1 = 0;
		this.rank2 = 0;
		this.rank3 = 0;
		this.rank4 = 0;
		this.rank5 = 0;
	}

	public Pelicula(int movieId, String titulo) {

		this.movieId = movieId;
		this.titulo = titulo;
		this.viewers = 0;
		this.ranking = 0.0;
		this.rank0 = 0;
		this.rank1 = 0;
		this.rank2 = 0;
		this.rank3 = 0;
		this.rank4 = 0;
		this.rank5 = 0;
	}

	//Para poder ordenar la lista (Short)
	@Override
    public int compareTo(Pelicula o) {
        return this.getRanking().compareTo(o.getRanking());
    }

	public void aumentarRanking(double rank){
		this.ranking+=rank;
	}

	public void aumentarViewers(){
		this.viewers++;
	}

	public double promedioRanking(){
		return Math.round((this.ranking / this.viewers) * 10) / 10.0;
	}

	public int[] getRanks(){
		int[] ranks={this.rank0, this.rank1, this.rank2, this.rank3, this.rank4, this.rank5};
		return ranks;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getViewers() {
		return viewers;
	}

	public Double getRanking() {
		return ranking;
	}

	public void setRanking(Double ranking) {
		this.ranking = ranking;
    }

	public void aumentarRank0(){
		this.rank0++;
	}

	public void aumentarRank1(){
		this.rank1++;
	}

	public void aumentarRank2(){
		this.rank2++;
	}

	public void aumentarRank3(){
		this.rank3++;
	}

	public void aumentarRank4(){
		this.rank4++;
	}

	public void aumentarRank5(){
		this.rank5++;
	}
    
	public Integer getRank0() {
		return rank0;
	}

	public Integer getRank1() {
		return rank1;
	}

	public Integer getRank2() {
		return rank2;
	}

	public Integer getRank3() {
		return rank3;
	}

	public Integer getRank4() {
		return rank4;
	}

	public Integer getRank5() {
		return rank5;
	}
}

package registros;

public class Total {
	
	private Integer movies;
	private Integer votos;
	private Integer usuarios;
	
	private Integer rank0;
	private Integer rank1;
	private Integer rank2;
	private Integer rank3;
	private Integer rank4;
	private Integer rank5;

	public Total() {

		this.movies = 0;
		this.votos = 0;
		this.usuarios = 0;
		this.rank0 = 0;
		this.rank1 = 0;
		this.rank2 = 0;
		this.rank3 = 0;
		this.rank4 = 0;
		this.rank5 = 0;
	}

	public Total(Integer movieId, Integer votos, Integer usuarios) {
		this.movies = movieId;
		this.votos = votos;
		this.usuarios = usuarios;
	}
	
	public Integer getMovies() {
		return movies;
	}
	
	public Integer getVotos() {
		return votos;
	}
	
	public Integer getUsuarios() {
		return usuarios;
	}

	public void aumentarUsuarios(){
		this.usuarios++;
	}

	public void aumentarVotos(){
		this.votos++;
	}

	public void aumentarPeliculas(){
		this.movies++;
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
}

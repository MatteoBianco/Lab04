package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente (Studente studente) {
		
		Studente s = new Studente(0, "", "", "");
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), 
						rs.getString("nome"), rs.getString("CDS"));
			}
			
			conn.close();

			return s;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Studente> getStudentiIscritti() {
		
		List<Studente> studenti = new LinkedList<Studente>();
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM iscrizione AS i, studente AS s WHERE i.matricola = s.matricola "
				+ "GROUP BY s.matricola";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				int matricola = rs.getInt("s.matricola");
				String cognome = rs.getString("s.cognome");
				String nome = rs.getString("s.nome");
				String CDS = rs.getString("s.CDS");
				
				studenti.add(new Studente(matricola, cognome, nome, CDS));
			}
			
			conn.close();
			
			return studenti;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
}

package br.com.rhscdeveloper.configuration;

import java.util.Date;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.exception.FlywayValidateException;

import br.com.rhscdeveloper.enumerator.Enums.Situacao;
import br.com.rhscdeveloper.enumerator.Enums.SituacaoMovimento;
import br.com.rhscdeveloper.enumerator.Enums.TipoCobranca;
import br.com.rhscdeveloper.enumerator.Enums.TipoMovimento;
import br.com.rhscdeveloper.exception.GlobalException;
import br.com.rhscdeveloper.model.MovimentoFinanceiroVO;
import br.com.rhscdeveloper.model.MovimentoVeiculoVO;
import br.com.rhscdeveloper.model.RegraFinanceiraVO;
import br.com.rhscdeveloper.model.VeiculoVO;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@Startup
@ApplicationScoped
public class Configuration {

	@ConfigProperty(name = "quarkus.datasource.jdbc.url") private String host;
	@ConfigProperty(name = "quarkus.datasource.username") private String user;
	@ConfigProperty(name = "quarkus.datasource.password") private String password;
	
	public void onStart(@Observes StartupEvent ev) {
		init();
    }
		
	public void applyMigrations() {
		
		Flyway flyway = null;
		
		try {
			flyway = Flyway.configure().dataSource(host, user, password).load();
			flyway.migrate();
		} catch (FlywayValidateException e) {
			e.printStackTrace();
			flyway.repair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	protected void init() {

		try {
//			applyMigrations();
			
			if(VeiculoVO.findAll().list().isEmpty()) {
				VeiculoVO.persist(new VeiculoVO("HB20", "Hyundai", new Date(), "OTO8226", new Date()));
				VeiculoVO.persist(new VeiculoVO("Onix", "Chevrolet", new Date(), "OTO8221", new Date()));
				VeiculoVO.persist(new VeiculoVO("HB20", "Hyundai", new Date(), "OTO8228", new Date()));
			}
			
			if(RegraFinanceiraVO.findAll().list().isEmpty()) {
				RegraFinanceiraVO.persist(new RegraFinanceiraVO("Hora semanal", 9.00, TipoCobranca.INDIFERENTE, TipoMovimento.DIA, new Date(), new Date(), Situacao.ATIVO, new Date()));
				RegraFinanceiraVO.persist(new RegraFinanceiraVO("Hora final de semana", 7.00, TipoCobranca.INDIFERENTE, TipoMovimento.FINAL_SEMANA, new Date(), new Date(), Situacao.ATIVO, new Date()));
				RegraFinanceiraVO.persist(new RegraFinanceiraVO("Mensalista em dinheiro", 250.50, TipoCobranca.DINHEIRO, TipoMovimento.MENSALISTA, new Date(), new Date(), Situacao.ATIVO, new Date()));
				RegraFinanceiraVO.persist(new RegraFinanceiraVO("Mensalista no cartão", 270.79, TipoCobranca.CREDITO, TipoMovimento.MENSALISTA, new Date(), new Date(), Situacao.ATIVO, new Date()));
				RegraFinanceiraVO.persist(new RegraFinanceiraVO("Fração de hora util indiferente", 5.50, TipoCobranca.INDIFERENTE, TipoMovimento.DIA, new Date(), new Date(), Situacao.ATIVO, new Date()));
				RegraFinanceiraVO.persist(new RegraFinanceiraVO("Fração de hora final de semana indiferente", 4.00, TipoCobranca.INDIFERENTE, TipoMovimento.FINAL_SEMANA, new Date(), new Date(), Situacao.ATIVO, new Date()));
			}
			
			if(MovimentoVeiculoVO.findAll().list().isEmpty()) {
				MovimentoVeiculoVO.persist(new MovimentoVeiculoVO(VeiculoVO.findAll().firstResult(), TipoMovimento.DIA.getId(), new Date(), new Date(), SituacaoMovimento.ENCERRADO.getId(), new Date()));
			}
			
			if(MovimentoFinanceiroVO.findAll().list().isEmpty()) {
				RegraFinanceiraVO rf = RegraFinanceiraVO.findAll().firstResult();
				MovimentoVeiculoVO mv = MovimentoVeiculoVO.findAll().firstResult();
				
				MovimentoFinanceiroVO.persist(new MovimentoFinanceiroVO(rf, mv, 2.00, SituacaoMovimento.ABERTO, new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException("Erro ao iniciar a aplicação");
		}
	}
}

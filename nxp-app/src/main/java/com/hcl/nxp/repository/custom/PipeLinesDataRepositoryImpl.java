package com.hcl.nxp.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.nxp.vo.PipeLinesData;

public class PipeLinesDataRepositoryImpl implements PipeLinesDataCustomRepository {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<PipeLinesData> fetchRecords(final Integer id) {
		final StringBuilder builder = new StringBuilder("select pld from PipeLinesData pld ");
		builder.append("where 1=1 ");
		if (id != null) {
			builder.append("and pipeLinesID.id=:id ");
		}
		final TypedQuery<PipeLinesData> typedQuery = entityManager.createQuery(builder.toString(), PipeLinesData.class);

		if (id != null) {
			typedQuery.setParameter("id", id);
		}
		final List<PipeLinesData> details = typedQuery.getResultList();

		return details;
	}

	@Override
	public String getLogs(final Integer id, final String name) {
		final StringBuilder builder = new StringBuilder("select logs from PipeLinesData pld ");
		builder.append("where 1=1 ");
		if (id != null) {
			builder.append("and pipeLinesID.id=:id ");
		}
		if (name != null) {
			builder.append("and headersDisplay=:header ");
		}
		final TypedQuery<String> typedQuery = entityManager.createQuery(builder.toString(), String.class);

		if (id != null) {
			typedQuery.setParameter("id", id);
		}
		if (name != null) {
			typedQuery.setParameter("header", name);
		}
		final String logs = typedQuery.getSingleResult();

		return logs != null ? logs : null;
	}

}

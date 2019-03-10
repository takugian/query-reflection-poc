package tg.com.queryreflectionpoc.core.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.query.Param;

import tg.com.queryreflectionpoc.repository.CadastroRepositoryPoc;

@SuppressWarnings("rawtypes")
public class InvocationHandlerImpl implements InvocationHandler {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String M_PERSIST = "persist";
	private static final String M_MERGE = "merge";
	private static final String M_REMOVE = "remove";
	private static final String M_FIND = "find";
	private static final String M_LIST = "list";
	private static final String M_LIST_BY = "listBy";
	private static final String M_LIST_ALL = "listAll";
	private static final String M_ORDER_BY = "OrderBy";
	private static final String C_ORDER_BY = " Order By ";
	private static final String EQ = " = ";
	private static final String DIF = " <> ";
	private static final String NIN = " Not In ";
	private static final String IN = " In ";
	private static final String NLIKE = " Not Like ";
	private static final String LIKE = " Like ";
	private static final String GTE = " >= ";
	private static final String GT = " > ";
	private static final String LTE = " <= ";
	private static final String LT = " < ";
	private static final String BET = " Between ";
	private static final String AND = " And ";
	private static final String OR = " Or ";
	private static final String DESC = " Desc ";
	private static final String ASC = " Asc ";

	public static void main(String args[]) {

		ClassLoader classLoader = CadastroRepositoryPoc.class.getClassLoader();
		Class[] interfaces = new Class[] { CadastroRepositoryPoc.class };

		CadastroRepositoryPoc repository = (CadastroRepositoryPoc) Proxy.newProxyInstance(classLoader, interfaces,
				new InvocationHandlerImpl());

		System.out.println(repository.listByNmCadastro("OI"));

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		String methodName = method.getName();

		if (methodName.equals("hashCode") || methodName.equals("equals")) {
			return 1;
		}

		org.springframework.data.jpa.repository.Query methodQuery = method
				.getAnnotation(org.springframework.data.jpa.repository.Query.class);
		Class<?> returnType = method.getReturnType();

		try {

			StringBuilder sql = new StringBuilder();

			if (methodName.equals(M_PERSIST)) {

				entityManager.persist(args[0]);
				return args[0];

			} else if (methodName.equals(M_MERGE)) {

				return entityManager.merge(args[0]);

			} else if (methodName.equals(M_REMOVE)) {

				entityManager.remove(args[0]);

			} else if (methodName.equals(M_FIND)) {

				return entityManager.find(returnType, args[0]);

			} else if (methodName.equals(M_LIST_ALL)) {

				sql.append(" SELECT o FROM ");
				sql.append(getClassGeneric(method).getSimpleName()).append(" o ");
				sql.append(" WHERE 1 = 1 ");

			} else if (methodQuery != null) {

				sql.append(methodQuery.name());

			} else if (methodName.contains(M_LIST)) {

				sql.append(" SELECT o FROM ");
				sql.append(getClassGeneric(method).getSimpleName()).append(" o ");
				sql.append(" WHERE 1 = 1 ");

				if (methodName.contains(M_LIST_BY)) {

					sql.append(AND);

					Integer positionParam = 1;
					for (Entry<Integer, String> entrySet : listSortedConditions(method)) {

						String fieldName = entrySet.getValue();
						String by = methodName.contains(M_ORDER_BY) ? methodName.substring(0, methodName.indexOf(M_ORDER_BY)) : methodName;
						String condition = getAsCondition(fieldName);
						String column = getAsColumn(fieldName);
						String param = getAsOrdinalParam(positionParam.toString());

						if (by.contains(condition.concat("BetweenOr"))) {
							sql.append(column).append(BET).append(param).append(AND)
									.append(getAsOrdinalParam((++positionParam).toString())).append(OR);
						} else if (by.contains(condition.concat("BetweenAnd"))) {
							sql.append(column).append(BET).append(param).append(AND)
									.append(getAsOrdinalParam((++positionParam).toString())).append(AND);
						} else if (by.contains(condition.concat("Between"))) {
							sql.append(column).append(BET).append(param).append(AND)
									.append(getAsOrdinalParam((++positionParam).toString()));
						} else if (by.contains(condition.concat("LessThanOr"))) {
							sql.append(column).append(LT).append(param).append(OR);
						} else if (by.contains(condition.concat("LessThanAnd"))) {
							sql.append(column).append(LT).append(param).append(AND);
						} else if (by.contains(condition.concat("LessThan"))) {
							sql.append(column).append(LT).append(param);
						} else if (by.contains(condition.concat("LessThanEqualOr"))) {
							sql.append(column).append(LTE).append(param).append(OR);
						} else if (by.contains(condition.concat("LessThanEqualAnd"))) {
							sql.append(column).append(LTE).append(param).append(AND);
						} else if (by.contains(condition.concat("LessThanEqual"))) {
							sql.append(column).append(LTE).append(param);
						} else if (by.contains(condition.concat("GreaterThanOr"))) {
							sql.append(column).append(GT).append(param).append(OR);
						} else if (by.contains(condition.concat("GreaterThanAnd"))) {
							sql.append(column).append(GT).append(param).append(AND);
						} else if (by.contains(condition.concat("GreaterThan"))) {
							sql.append(column).append(GT).append(param);
						} else if (by.contains(condition.concat("GreaterThanEqualOr"))) {
							sql.append(column).append(GTE).append(param).append(OR);
						} else if (by.contains(condition.concat("GreaterThanEqualAnd"))) {
							sql.append(column).append(GTE).append(param).append(AND);
						} else if (by.contains(condition.concat("GreaterThanEqual"))) {
							sql.append(column).append(GTE).append(param);
						} else if (by.contains(condition.concat("LikeOr"))) {
							sql.append(column).append(LIKE).append(param).append(OR);
						} else if (by.contains(condition.concat("LikeAnd"))) {
							sql.append(column).append(LIKE).append(param).append(AND);
						} else if (by.contains(condition.concat("Like"))) {
							sql.append(column).append(LIKE).append(param);
						} else if (by.contains(condition.concat("NotLikeOr"))) {
							sql.append(column).append(NLIKE).append(param).append(OR);
						} else if (by.contains(condition.concat("NotLikeAnd"))) {
							sql.append(column).append(NLIKE).append(param).append(AND);
						} else if (by.contains(condition.concat("NotLike"))) {
							sql.append(column).append(NLIKE).append(param);
						} else if (by.contains(condition.concat("InOr"))) {
							sql.append(column).append(IN).append(param).append(OR);
						} else if (by.contains(condition.concat("InAnd"))) {
							sql.append(column).append(IN).append(param).append(AND);
						} else if (by.contains(condition.concat("In"))) {
							sql.append(column).append(IN).append(param);
						} else if (by.contains(condition.concat("NotInOr"))) {
							sql.append(column).append(NIN).append(param).append(OR);
						} else if (by.contains(condition.concat("NotInAnd"))) {
							sql.append(column).append(NIN).append(param).append(AND);
						} else if (by.contains(condition.concat("NotIn"))) {
							sql.append(column).append(NIN).append(param);
						} else if (by.contains(condition.concat("NotOr"))) {
							sql.append(column).append(DIF).append(param).append(OR);
						} else if (by.contains(condition.concat("NotAnd"))) {
							sql.append(column).append(DIF).append(param).append(AND);
						} else if (by.contains(condition.concat("Not"))) {
							sql.append(column).append(DIF).append(param);
						} else if (by.contains(condition.concat("Or"))) {
							sql.append(column).append(EQ).append(param).append(OR);
						} else if (by.contains(condition.concat("And"))) {
							sql.append(column).append(EQ).append(param).append(AND);
						} else if (by.contains(condition)) {
							sql.append(column).append(EQ).append(param);
						}

						positionParam++;

					}

				}

				if (methodName.contains(M_ORDER_BY)) {

					sql.append(C_ORDER_BY);

					for (Entry<Integer, String> entrySet : listSortedConditions(method)) {

						String orderBy = methodName.substring(methodName.indexOf(M_ORDER_BY));
						String fieldName = entrySet.getValue();
						String column = getAsColumn(fieldName);
						String condition = getAsCondition(fieldName);

						if (orderBy.contains(condition.concat("DescAnd"))) {
							sql.append(column).append(DESC).append(" , ");
						} else if (orderBy.contains(condition.concat("Desc"))) {
							sql.append(column).append(DESC);
						} else if (orderBy.contains(condition.concat("AscAnd"))) {
							sql.append(column).append(ASC).append(" , ");
						} else if (orderBy.contains(condition.concat("Asc"))) {
							sql.append(column).append(ASC);
						}

					}

				}

			}

			System.out.println(sql.toString());

			Integer positionParam = 1;
			Integer indexArgs = 0;
			Query query = entityManager.createQuery(sql.toString());
			for (Parameter parameter : method.getParameters()) {
				Param param = parameter.getAnnotation(Param.class);
				if (param != null) {
					query.setParameter(positionParam, args[indexArgs]);
					positionParam++;
					indexArgs++;
				} else if (parameter.getType().equals(Pagination.class)) {
					Pagination pagination = (Pagination) args[args.length - 1];
					query.setFirstResult((pagination.getPageNumber() - 1) * pagination.getPageSize());
					query.setMaxResults(pagination.getPageSize());
					// pagination.setTotalElements(totalElements)TODO Pendente
				}
			}

			if (sql.toString().contains("SELECT")) {
				if (methodName.equals(M_FIND)) {
					return query.getSingleResult();
				} else {
					return query.getResultList();
				}
			} else {
				return query.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BancoDeDadosException(e);
		}

	}

	private Set<Entry<Integer, String>> listSortedConditions(Method method) {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		for (Field field : getClassGeneric(method).getDeclaredFields()) {
			String condition = getAsCondition(field.getName());
			if (method.getName().indexOf(condition) != -1) {
				treeMap.put(method.getName().indexOf(condition), field.getName());
			}
		}
		return treeMap.entrySet();
	}

	public Class getClassGeneric(Method method) {
		ParameterizedType parameterizedType = (ParameterizedType) method.getGenericReturnType();
		Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
		return clazz;
	}

	public String getAsProperty(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	public String getAsCondition(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public String getAsColumn(String name) {
		return "o.".concat(getAsProperty(name));
	}

	public String getAsOrdinalParam(String name) {
		return "?".concat(getAsProperty(name));
	}

}

class BancoDeDadosException extends RuntimeException {

	public BancoDeDadosException(Exception e) {
		super(e);
	}

}

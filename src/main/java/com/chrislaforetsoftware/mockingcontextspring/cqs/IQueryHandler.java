package com.chrislaforetsoftware.mockingcontextspring.cqs;

public interface IQueryHandler<Q extends IQuery, R> {

	public R handle(Q query);
}

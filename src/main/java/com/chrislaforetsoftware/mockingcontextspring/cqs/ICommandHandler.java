package com.chrislaforetsoftware.mockingcontextspring.cqs;

public interface ICommandHandler<C extends ICommand, R> {

	public R handle(C command);
}

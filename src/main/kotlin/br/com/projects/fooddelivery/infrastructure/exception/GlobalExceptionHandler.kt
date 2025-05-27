package br.com.projects.fooddelivery.infrastructure.exception

import br.com.projects.fooddelivery.infrastructure.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = 404,
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(ConflictException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handlerConflict(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = 409,
            error = HttpStatus.CONFLICT.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(GenericException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerGeneric(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = 500,
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(DomainValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun domainValidation(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = 400,
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message,
            path = request.servletPath
        )
    }

}
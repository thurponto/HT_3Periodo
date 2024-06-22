import express from 'express'
class AppError {
    message
    statusCode

    constructor(message: string, statusCode = 400) {
        this.message = message
        this.statusCode = statusCode
    }
}

export default AppError
import { Request, Response, NextFunction } from "express";
import { verify } from "jsonwebtoken";
import authConfig from '../config/auth'
import AppError from "../utils/AppError";

function autenticacao(
    req: Request,
    res: Response,
    next: NextFunction
) {

    const authHeader = req.headers.authorization

    if (!authHeader) {
        throw new AppError('Token invalido', 401)
    }

    const [, token] = authHeader.split(" ")

    try {

        const dadosToken =
            verify(token, authConfig.jwt.secret)

            console.log(dadosToken)
            console.log("dadosToken payload")

        return next()

    } catch (error) {
        throw new AppError("Token invalido", 401)
    }
}

export default autenticacao
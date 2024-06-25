import { DataTypes, Model } from 'sequelize';
import bcrypt from 'bcryptjs';
import sequelize from '../database/database';
class UserModel extends Model {
  public id!: number;
  public nome!: string;
  public telefone!: string;
  public cpf!: string;
  public dataNascimento!: Date;
  public sexo!: string;
  public cep!: string;
  public senha!: string;

  public static initialize(): void {
    this.init(
      {
        nome: {
          type: DataTypes.STRING,
          allowNull: false,
        },
        telefone: {
          type: DataTypes.STRING,
          allowNull: false,
        },
        cpf: {
          type: DataTypes.STRING,
          allowNull: false,
          unique: true,
        },
        dataNascimento: {
          type: DataTypes.DATEONLY,
          allowNull: false,
        },
        sexo: {
          type: DataTypes.STRING,
          allowNull: false,
        },
        cep: {
          type: DataTypes.STRING,
          allowNull: false,
        },
        senha: {
          type: DataTypes.STRING,
          allowNull: false,
        },
      },
      {
        sequelize,
        tableName: 'users',
        modelName: 'UserModel',
        hooks: {
          beforeCreate: async (user: UserModel) => {
            const hashedPassword = await bcrypt.hash(user.senha, 10);
            user.senha = hashedPassword;
          },
        },
      }
    );
  }

  public async verifyPassword(password: string): Promise<boolean> {
    return bcrypt.compare(password, this.senha);
  }
}

UserModel.initialize();

export default UserModel;

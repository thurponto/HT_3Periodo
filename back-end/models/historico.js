const Sequelize = require('sequelize');
module.exports = function(sequelize, DataTypes) {
  return sequelize.define('historico', {
    id: {
      autoIncrement: true,
      type: DataTypes.BIGINT,
      allowNull: false,
      primaryKey: true
    },
    idoso_id: {
      type: DataTypes.BIGINT,
      allowNull: true,
      references: {
        model: 'idoso',
        key: 'id'
      }
    },
    agenda_id: {
      type: DataTypes.BIGINT,
      allowNull: true,
      references: {
        model: 'agenda',
        key: 'id'
      }
    },
    vacina_id: {
      type: DataTypes.BIGINT,
      allowNull: true,
      references: {
        model: 'vacina',
        key: 'id'
      }
    },
    alergias: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    condicoes_medicas: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    observacoes: {
      type: DataTypes.TEXT,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'historico',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id" },
        ]
      },
      {
        name: "idoso_id",
        using: "BTREE",
        fields: [
          { name: "idoso_id" },
        ]
      },
      {
        name: "agenda_id",
        using: "BTREE",
        fields: [
          { name: "agenda_id" },
        ]
      },
      {
        name: "vacina_id",
        using: "BTREE",
        fields: [
          { name: "vacina_id" },
        ]
      },
    ]
  });
};

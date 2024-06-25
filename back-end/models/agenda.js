const Sequelize = require('sequelize');
module.exports = function(sequelize, DataTypes) {
  return sequelize.define('agenda', {
    id: {
      autoIncrement: true,
      type: DataTypes.BIGINT,
      allowNull: false,
      primaryKey: true
    },
    agente_saude_id: {
      type: DataTypes.BIGINT,
      allowNull: true,
      references: {
        model: 'agente_saude',
        key: 'id'
      }
    },
    data: {
      type: DataTypes.DATEONLY,
      allowNull: true
    },
    horario: {
      type: DataTypes.TIME,
      allowNull: true
    },
    idoso_id: {
      type: DataTypes.BIGINT,
      allowNull: true,
      references: {
        model: 'idoso',
        key: 'id'
      }
    }
  }, {
    sequelize,
    tableName: 'agenda',
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
        name: "agente_saude_id",
        using: "BTREE",
        fields: [
          { name: "agente_saude_id" },
        ]
      },
      {
        name: "fk_idoso",
        using: "BTREE",
        fields: [
          { name: "idoso_id" },
        ]
      },
    ]
  });
};

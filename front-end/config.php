<?php
    $servidor = "localhost";
    $usuario  = "root";
    $senha    = "";
    $banco    = "saude";

    
    try {
        $pdo = new PDO("mysql:host={$servidor};dbname={$banco};port=3000;charset=utf8;",$usuario,$senha);
    } catch (Exception $e) {
        echo "<p>Erro ao tentar conectar</p>";
        echo $e->getMessage();
    }

    function formatarValor($valor) {

        $valor = str_replace(".","", $valor);
        return str_replace(",",".", $valor);
    }
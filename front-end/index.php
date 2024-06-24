<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN</title>
    <base href="<?php echo "http://" . $_SERVER["HTTP_HOST"] . rtrim(dirname($_SERVER["PHP_SELF"]), '/\\') . "/"; ?>">

    <link rel="stylesheet" href="css/styles.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body id="page-top">
<?php
    require "funcoes.php";

    if(isset($_GET['param'])){
        $page = explode("/", $_GET['param']);
        
        $pasta = $page[0] ?? NULL;
        $arquivo = $page[1] ?? NULL;
        $id = $page[2] ?? NULL;

        $page = "$pasta/$arquivo";

        require "header.php";

        if(file_exists("$page.php")) {
            require "$page.php";
        } else {
            require "pages/erro.php";
        }

        require "footer.php";
    } else {
        require "pages/login.php";
    }
?>
</body>
</html>

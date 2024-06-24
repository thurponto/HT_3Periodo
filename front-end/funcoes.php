<?php
    function mensagemErro($msg) {
        ?>
        <script>
            Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '<?=$msg?>',
            }).then((result) => {
                history.back(); 
            })
        </script>
        <?php
        exit;
    } 
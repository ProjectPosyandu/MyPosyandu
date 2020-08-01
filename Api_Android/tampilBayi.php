<?php
require("config.php");
$perintah = "SELECT * FROM tb_bayi";
$eksekusi = mysqli_query($con,$perintah);
$cek = mysqli_affected_rows($con);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"]= "Data tersedia";
    $response["data"] = array();
    
    while($ambil = mysqli_fetch_object($eksekusi)){
        $D["id_bayi"] = $ambil->id_bayi;
        $D["nama_bayi"] = $ambil ->nama_bayi;
        $D["jenis_kelamin"] = $ambil ->jenis_kelamin;
        $D["tgl_lahir"] = $ambil ->tgl_lahir;
        
        array_push($response["data"], $D);
    }
}else{
    $response["kode"] = 0;
    $response["pesan"]= "Data tidak tersedia";
}

echo json_encode($response);
mysqli_close($con);

?>
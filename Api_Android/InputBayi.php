<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
// json response array
$response = array("error" => FALSE);
if (isset($_POST['nama_bayi']) && isset($_POST['tgl_lahir']) && isset($_POST['jenis_kelamin'])) {
    // menerima parameter POST ( nama, email, password )
    $nama = $_POST['nama_bayi'];
    $tgl = $_POST['tgl_lahir'];
    $jk = $_POST['jenis_kelamin'];
    
    $bayi = $db->simpanBayi($nama, $tgl, $jk);
    if ($bayi) {
        // simpan user berhasil
        $response["error"] = FALSE;
        $response["uid"] = $bayi["id_bayi"];
        $response["bayi"]["nama_bayi"] = $bayi["nama_bayi"];
        $response["bayi"]["tgl_lahir"] = $bayi["tgl_lahir"];
        $response["bayi"]["jenis_kelamin"] = $bayi["jenis_kelamin"];
        $response["bayi"]["id_user"] = $bayi["id_user"];
        echo json_encode($response);
    } else {
        // gagal menyimpan user
        $response["error"] = TRUE;
        $response["error_msg"] = "Terjadi kesalahan saat melakukan registrasi";
        echo json_encode($response);
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Parameter (nama, tanggal lahir, jenis kelamin) ada yang kurang";
    echo json_encode($response);
}
?>
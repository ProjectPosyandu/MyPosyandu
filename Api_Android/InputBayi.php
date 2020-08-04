<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
// json response array
$response = array("error" => FALSE);

if (isset($_POST['nama_bayi']) && isset($_POST['tgl_lahir']) && isset($_POST['jenis_kelamin']) && isset($_POST['id']) && isset($_POST['berat_bayi']) && isset($_POST['tinggi_bayi'])) {
    // menerima parameter POST 
    $nama_bayi = $_POST['nama_bayi'];
    $tgl_lahir = $_POST['tgl_lahir'];
    $jenis_kelamin = $_POST['jenis_kelamin'];
    $foto_bayi = $_POST['foto_bayi'];
    $id = $_POST['id'];
    $usia_bayi = "0";
    $berat_bayi = $_POST['berat_bayi'];
    $tinggi_bayi = $_POST['tinggi_bayi'];
    // Cek jika user ada dengan email yang sama
    if ($db->isBayiExisted($nama_bayi)) {
        // user telah ada
        $response["error"] = TRUE;
        $response["error_msg"] = "bayi telah ada dengan nama " . $nama_bayi;
        echo json_encode($response);
    } else {
        // buat user baru
        $user = $db->simpanBayi($nama_bayi, $tgl_lahir, $jenis_kelamin, $foto_bayi, $id);
        if ($user) {
        	$id_bayi = $user["id_bayi"];

        	$detail = $db->simpanDetailBayi($id_bayi,$usia_bayi,$berat_bayi,$tinggi_bayi);
            // simpan user berhasil
            $response["error"] = FALSE;
            $response["uid"] = $user["id_bayi"];
            $response["user"]["id_bayi"] = $user["id_bayi"];
            $response["user"]["id"] = $user["id"];
            echo json_encode($response);
        } else {
            // gagal menyimpan user
            $response["error"] = TRUE;
            $response["error_msg"] = "Terjadi kesalahan saat input";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Parameter ada yang kurang";
    echo json_encode($response);
}
?>
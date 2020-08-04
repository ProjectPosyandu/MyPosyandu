<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
// json response array
$response = array("error" => FALSE);

if (isset($_POST['id_bayi']) && isset($_POST['usia_bayi']) && isset($_POST['berat_bayi']) && isset($_POST['tinggi_bayi'])) {
    // menerima parameter POST 
    $id_bayi = $_POST['id_bayi'];
    $usia_bayi = $_POST['usia_bayi'];
    $berat_bayi = $_POST['berat_bayi'];
    $tinggi_bayi = $_POST['tinggi_bayi'];
    
    $user = $db->simpanDetailBayi($id_bayi,$usia_bayi,$berat_bayi,$tinggi_bayi);
        if ($user) {
            // simpan user berhasil
            $response["error"] = FALSE;
            $response["uid"] = $user["id_bayi"];
            $response["user"]["id_bayi"] = $user["id_bayi"];
            echo json_encode($response);
        } else {
            // gagal menyimpan user
            $response["error"] = TRUE;
            $response["error_msg"] = "Terjadi kesalahan saat input";
            echo json_encode($response);
        }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Parameter ada yang kurang";
    echo json_encode($response);
}
?>
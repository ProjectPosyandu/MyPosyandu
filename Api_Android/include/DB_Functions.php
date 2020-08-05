<?php
class DB_Functions {
    private $conn;
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // koneksi ke database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
    // destructor
    function __destruct() {
         
    }
    public function simpanUser($nama, $email, $password) {
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
        $stmt = $this->conn->prepare("INSERT INTO tb_user(unique_id, nama, email, encrypted_password, salt) VALUES(?, ?, ?, ?, ?)");
        $stmt->bind_param("sssss", $uuid, $nama, $email, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();
        // cek jika sudah sukses
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM tb_user WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return false;
        }
    }
    /**
     * Get user berdasarkan email dan password
     */
    public function getUserByEmailAndPassword($email, $password) {
        $stmt = $this->conn->prepare("SELECT * FROM tb_user WHERE email = ?");
        $stmt->bind_param("s", $email);
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            // verifikasi password user
            $salt = $user['salt'];
            $encrypted_password = $user['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password);
            // cek password jika sesuai
            if ($encrypted_password == $hash) {
                // autentikasi user berhasil
                return $user;
            }
        } else {
            return NULL;
        }
    }

    /**
     * Cek User ada atau tidak
     */
    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from tb_user WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        if ($stmt->num_rows > 0) {
            // user telah ada 
            $stmt->close();
            return true;
        } else {
            // user belum ada 
            $stmt->close();
            return false;
        }
    }
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    public function checkhashSSHA($salt, $password) {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
        return $hash;
    }

     /**
     * Get user berdasarkan id
     */
    public function getUserAccount($id) {
 
        $stmt = $this->conn->prepare("SELECT * FROM tb_user WHERE unique_id = ?");
        $stmt->bind_param("s", $id); 
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user; 
        } else {
            return NULL;
        }
    }

        /**
     * Insert data Bayi
     */

    public function simpanBayi($nama_bayi,$tgl_lahir,$jenis_kelamin,$foto_bayi, $id, $kode){
        $path = "images/$nama_bayi.jpeg";

        $stmt = $this->conn->prepare("INSERT INTO tb_bayi(id_bayi,nama_bayi, tgl_lahir, jenis_kelamin, foto_bayi, id) VALUES(?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("ssssss", $id_bayi, $nama_bayi, $tgl_lahir, $jenis_kelamin, $path, $id);
        $result = $stmt->execute();
        $stmt->close();
        // cek jika sudah sukses
        if ($result) {
            if (file_put_contents($path, base64_decode($foto_bayi))){

            $stmt = $this->conn->prepare("SELECT * FROM tb_bayi WHERE nama_bayi = ?");
            $stmt->bind_param("s", $nama_bayi);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;

        } else {
            return false;
        }
    }
            
        
        
    }

    public function simpanDetailBayi($id_bayi,$id_usia,$berat_bayi,$tinggi_bayi){

        $stmt = $this->conn->prepare("INSERT INTO tb_detail_bayi(id_bayi, id_usia, berat_bayi, tinggi_bayi) VALUES(?, ?, ?, ?)");
        $stmt->bind_param("ssss", $id_bayi, $id_usia, $berat_bayi, $tinggi_bayi);
        $result = $stmt->execute();
        $stmt->close();
        // cek jika sudah sukses
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM tb_detail_bayi WHERE id_bayi = ?");
            $stmt->bind_param("s", $id_bayi);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return false;
        }
    }

    public function isUsiaExisted($usia_bayi){
        $stmt = $this->conn->prepare("SELECT id_usia from tb_usia WHERE usia = ?");
        $stmt->bind_param("s", $usia_bayi);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            $stmt->close();
            // user telah ada 
            $stmt = $this->conn->prepare("SELECT * from tb_usia WHERE usia = ?");
            $stmt->bind_param("s", $usia_bayi);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            
            return $user;
        } else {
            // user belum ada 
            $stmt->close();
            return false;
        }
    }

    public function isBayiExisted($nama_bayi) {
        $stmt = $this->conn->prepare("SELECT nama_bayi from tb_bayi WHERE nama_bayi = ?");
        $stmt->bind_param("s", $nama_bayi);
        $stmt->execute();
        $stmt->store_result();
        if ($stmt->num_rows > 0) {
            // user telah ada 
            $stmt->close();
            return true;
        } else {
            // user belum ada 
            $stmt->close();
            return false;
        }
    }

    public function getBayi() {
 
        $stmt = $this->conn->prepare("SELECT * FROM tb_bayi");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user; 
        } else {
            return NULL;
        }
    }


    public function getDataPerBayi($id) {
 
        $stmt = $this->conn->prepare("SELECT * FROM tb_bayi WHERE id_bayi = ?");
        $stmt->bind_param("s", $id);
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user; 
        } else {
            return false;
        }
    }

    public function createIdBayi(){
        $stmt = $this->conn->prepare("SELECT MAX(id_bayi) as idbayi FROM tb_bayi");
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user; 
        } else {
            return false;
        }
    }


}
?>

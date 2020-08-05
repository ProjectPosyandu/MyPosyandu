<?php
require "config.php";

$query = "SELECT * FROM tb_usia
			JOIN tb_kriteria_bb_pr ON tb_usia.id_usia=tb_kriteria_bb_pr.id_usia";

	$hasil = mysqli_query($con,$query);
	if (mysqli_num_rows($hasil)>0) {
		$response = array();
		 if(mysqli_num_rows($hasil) > 0){  
			 while ($row = mysqli_fetch_assoc($hasil)) {
			 	$tampil['usia'] = $row['usia'];
			 	$tampil['kurang'] = $row['kurang'];
			 	$tampil['ideal_bawah'] = $row['ideal_bawah'];
			 	$tampil['ideal'] = $row['ideal'];
			 	$tampil['ideal_atas'] = $row['ideal_atas'];
			 	$tampil['lebih'] = $row['lebih'];
		        $response[] = $tampil;
		    }
	    echo json_encode(array("data" => $response) );
		}
	}

?>
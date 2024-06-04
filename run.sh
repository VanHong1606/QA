mvn clean verify -Denvironment=${1} -DplatformName=${2} -Dudid=${3} -Dtags=@${4} -Dusername=${5} -Dpassword=${6} -DdefaultAccountForTransfer=${7} -DproviderId=${8} -DmerchantId=${9}
pkill -9 -f appium
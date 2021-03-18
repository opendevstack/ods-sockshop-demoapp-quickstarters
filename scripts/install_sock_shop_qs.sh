#!/usr/bin/env bash

set -eu

ODS_CORE_HOME='/home/openshift/opendevstack/ods-core/'
ODS_CONFIGURATION_HOME='/home/openshift/opendevstack/ods-configuration/'
SSDQS="
    # sock-shop-demo quickstarters
    jenkinspipeline.quickstarter.demo-app-sockshop-payment.desc=Demo App - SockShop Payment
    jenkinspipeline.quickstarter.demo-app-sockshop-payment.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-shipping.desc=Demo App - SockShop Shipping
    jenkinspipeline.quickstarter.demo-app-sockshop-shipping.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-loadgen.desc=Demo App - SockShop Load / Integration Tests
    jenkinspipeline.quickstarter.demo-app-sockshop-loadgen.repo=ods-quickstarters
    
    jenkinspipeline.quickstarter.demo-app-sockshop-front-end.desc=Demo App - SockShop Front-end
    jenkinspipeline.quickstarter.demo-app-sockshop-front-end.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-carts.desc=Demo App - SockShop Carts
    jenkinspipeline.quickstarter.demo-app-sockshop-carts.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-catalogue.desc=Demo App - SockShop Catalogue
    jenkinspipeline.quickstarter.demo-app-sockshop-catalogue.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-orders.desc=Demo App - SockShop Orders
    jenkinspipeline.quickstarter.demo-app-sockshop-orders.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-queue-master.desc=Demo App - SockShop Queue Master
    jenkinspipeline.quickstarter.demo-app-sockshop-queue-master.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-user.desc=Demo App - SockShop User
    jenkinspipeline.quickstarter.demo-app-sockshop-user.repo=ods-quickstarters

    jenkinspipeline.quickstarter.demo-app-sockshop-tests.desc=Demo App - SockShop E2E Tests
    jenkinspipeline.quickstarter.demo-app-sockshop-tests.repo=ods-quickstarters
"
function add_sock_shop_demo_qs() {

  echo "Starting EDP Project Type configuration in ProvApp"
  echo "...some debug information first"
  oc login -u developer -p password
  oc whoami
  oc project ods

  CM=$(oc get -o yaml cm/additional-templates.properties)
  echo "${CM//properties: |/properties: |$SSDQS}" > cm.yaml

  echo "... Apply configmap change"
  oc apply -f cm.yaml
  
  echo "...restart ProvApp to reload new configuration"

  oc rollout latest dc/ods-provisioning-app
}

function add_qs_to_bitbucket() {
  scriptPath=$PWD
  pushd /tmp
  
  git clone ssh://git@bitbucket.odsbox.lan:7999/opendevstack/ods-quickstarters.git
  cd ods-quickstarters
  git checkout 3.x 
  cp -R $scriptPath/../demo* /tmp/ods-quickstarters
  git add .
  git commit -m "Sock Shop Demo QS"
  git push
  rm -Rf /tmp/ods-quickstarters
  popd
}
#add_sock_shop_demo_qs
add_qs_to_bitbucket




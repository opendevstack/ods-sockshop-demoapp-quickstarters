# ods-sockshop-demoapp-quickstarters

An OpenDevStack demo application based on https://github.com/microservices-demo.

## Samples

### Provisioning App Properties
You can find a sample prov-app.properties file for the Provisioning App in the sample folder. 

If you own a Provisioning App installation, you can apply these properties to the existing OpenShift ConfigMap of your ODS installation.

### Release Manager Configuration
You can find a sample metadata.yaml file for the Release Manager in the sample folder. 

To make it work, change all occurrences of *sock* to the *id* of your Jira project in that file.

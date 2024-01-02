# Gold Certificate System

The Gold Certificate System is a platform designed to issue certificates for every piece of gold produced in a workshop. This system streamlines the process for workshop agents to request certificate issuance. also this system will help to implement "Gold Online Factor Issuance System" (GOFIS)

## Features

- **Agent Authentication:** Agents must log in to the system to request a certificate for a piece of gold.

- **GCI Authentication:** The system uses the GCI Authentication module for registering and authenticating agents.

- **Keycloak Integration:** Keycloak is utilized as a resource server for authentication and authorization purposes.

## System Components

1. **Gold Certificate System:** The main application for issuing certificates.

2. **GCI Authentication:** Manages agent registration and authentication.

3. **Keycloak Integration:** Ensures secure authentication and authorization through Keycloak.

## Usage

1. **Agent Login:**
   - Agents need to log in to the system using their credentials.(username and password)

2. **Certificate Issuance:**
   - Once logged in, agents can request the issuance of a certificate for a piece of gold.

## Getting Started

1. **Prerequisites:**
   - Ensure you have Keycloak installed and configured.

2. **Installation:**
   - Clone the repository.
   - Configure the system with the necessary environment variables.

3. **Run the System:**
   - Start the Gold Certificate System and GCI Authentication modules.

4. **Agent Registration:**
   - Agents need to register through the GCI Authentication module.

5. **Agent Authentication:**
   - Agents can log in using their credentials to access the Gold Certificate System.

## Configuration

- **Keycloak Configuration:**
  - Configure Keycloak settings for proper authentication and authorization.

## API Documentation

The Gold Certificate System API is documented using Swagger. You can access the API documentation by navigating to the following endpoint:

- **Swagger UI:** [http://localhost8091/swagger-ui/index.html](http://localhost:8091/swagger-ui/index.html)

- **Swagger JSON:** [http://localhost:8091/api-docs](http://localhost:8091/api-docs)

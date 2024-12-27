# Adapter Design Pattern for User Provisioning in IAM

## Problem

When provisioning users across different Identity and Access Management (IAM) systems like **Azure**, **Keycloak**, **Okta**, and **MiniOrange**, the user attributes (e.g., name, email, roles, etc.) may have different formats or naming conventions in each system. This causes problems when you try to integrate them into a unified provisioning pipeline.

For example:
- **Azure** might use `userPrincipalName` for email, `givenName` for first name, etc.
- **Keycloak** might use `email` for email, `firstName` for first name, etc.
- **Okta** might use `login` for email, `firstName` for first name, etc.
- **MiniOrange** might have its own mapping, like `userEmail` and `userFirstName`.

### Solution

The **Adapter Design Pattern** can be used to convert the **user object** from one system into the format required by another system. The Adapter acts as a middle layer that ensures compatibility between systems by converting or mapping the attributes accordingly.

### When to Use Adapter Design Pattern

The Adapter pattern is useful in scenarios like:
- When you need to adapt or convert one system’s objects into the format required by another system.
- When integrating multiple external services (like Okta, Keycloak, Azure, etc.) with different APIs, and you need to normalize the user data for a unified interface.
- When systems have incompatible interfaces but need to collaborate.


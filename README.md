# Diary

## Table of contents
1. [Introduction](#introduction)
2. [What is a token?](#token_definition)
    - [Json Web Token (JWT)](#jwt_information)
    - [JWT Claims](#jwt_claims)
    - [JWT Signature](#jwt_signature)
    

## Introduction <a name="introduction"></a>
Welcome to the diary spring boot project. This project was created to learn how to set up spring
security in the application. Here we learn about the filter chain, tokens, JWT and more.

## What is a token? <a name="token_definition"></a>
In information technology, a ***token refers to a piece of data or authentication code that represents 
a user's identity or access rights***. It can be used for various purposes like authentication, 
authorization, or secure communication between systems. 

### Json Web Token (JWT) <a name="jwt_information"></a>
**JWT stands for JSON Web Token**. It is a compact, self-contained means of transmitting information 
between parties as a JSON object. JWTs are used for securely transmitting information between a 
client and a server.

They consist of three parts: 
- a **header** specifying the type of token and the hashing algorithm used, 
- a **payload** containing the actual information (***claims***) being transmitted,
- a **signature** that ensures the integrity of the token.

JWTs are commonly used for authentication and authorization in web applications. Once a user is 
authenticated, a JWT is often generated and sent back to the client, which is then included in 
subsequent requests to access protected routes or resources on the server. They are portable, 
stateless, and can securely carry information, reducing the need for the server to store session state.

### JWT Claims <a name="jwt_claims"></a>
Token claims refer to the pieces of information or assertions contained within a token's payload. These 
***claims provide details about the entity (user, system, etc.) associated with the token or specify access 
rights, metadata, or any other relevant information***.

There are three types of claims in a JSON Web Token (JWT):
- **Reserved Claims** - These are predefined claims standardized by the JWT specification. 
Some common reserved claims include ***iss*** (issuer), ***sub*** (subject), ***exp*** (expiration time), 
***iat*** (issued at), and ***nbf*** (not before).
- **Public Claims** - These are custom claims defined by the token issuer and are meant to convey additional 
information relevant to the application or system using the token. They are not standardized and can vary 
depending on the specific use case.
- **Private Claims** - These are custom claims agreed upon between parties that share the token. They are not 
registered or public and are used for specific purposes within the context of those parties' applications or 
systems.

Claims provide context and information about the token, allowing the recipient to make decisions regarding 
access, permissions, or other actions based on the data within the token payload.

### JWT Signature <a name="jwt_signature"></a>
Token signatures are generated using cryptographic algorithms that ensure the integrity and authenticity 
of the token. 

The process typically involves the following steps:
1. **Header and Payload Creation** - The token consists of a header (containing information about the 
token type and the hashing algorithm used) and a payload (containing the actual data or claims).
2. **Encoding** - Both the header and payload are encoded using a specified algorithm such as Base64.
3. **Concatenation** - The encoded header and payload are concatenated together, usually separated by a 
dot (.) to form the message that will be signed.
4. **Signing the Message** - The concatenated message is signed using a cryptographic algorithm (e.g., HMAC, RSA) 
and a secret key (in the case of symmetric algorithms) or a private key (in the case of asymmetric algorithms).
5. **Generation of Signature** - The signature is produced by applying the chosen algorithm to the concatenated 
message and the secret/private key. This process generates a unique string of characters that serves as the 
token's signature.
6. **Appending the Signature** - The generated signature is then appended to the token, creating a complete and signed token.

Upon receiving the token, the recipient can verify its authenticity by extracting the header and payload, re-signing the 
concatenated message with the appropriate key, and comparing the generated signature with the received signature. If they 
match, it confirms the token's integrity and authenticity.
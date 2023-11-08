"# EstateMaster" 
A Java-based real estate management system with a Command-Line Interface (CLI) that incorporates design patterns, UML diagrams, and multithreading.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Unit Testing](#unit-testing)
- [Multithreading](#multithreading)


## Introduction

This project is a Java-based Real Estate Management System that provides robust property and tenant management functionalities. It adheres to the specified requirements, including design patterns, UML diagrams, and multithreading, and is intended for use in a CLI environment.

## Features

- Add properties with details.
- Add tenants and associate them with properties.
- Rent units to tenants.
- Display a list of all properties.
- Display a list of all tenants.
- Display a list of rented units.
- Display a list of vacant units.
- Display a list of all leases.

## Installation

Clone the repository and compile the code using a Java development environment. Make sure you have JavaFX set up, as this project uses it for the CLI.

## Unit Testing

The project includes a suite of unit tests to ensure robust functionality.

##MultiThreading

The system's property display functionality runs on a separate thread to enhance performance. The GUI thread is isolated from business logic processes to ensure a responsive user interface.

# NFR-05.001 Service Availability

## Description

Production services must maintain a target uptime of 90% (allowing approximately 72 hours of downtime per month). Automated health checks must run every 5 minutes. Automatic failover must be implemented for stateless services. The system must degrade gracefully when dependent services fail.

## Rationale

While 90% uptime allows planned maintenance windows, automated health checks and failover ensure rapid detection and recovery from failures. Graceful degradation prevents a single service failure from taking down the entire system. Addresses the reliability and availability quality attributes.

## Verification Method

Verified by monitoring uptime over a defined period and calculating availability percentage. Health check endpoint must be tested for correct status reporting. Failover must be tested by stopping a stateless service instance and confirming traffic is redirected to remaining instances. Graceful degradation must be tested by simulating dependent service failures.

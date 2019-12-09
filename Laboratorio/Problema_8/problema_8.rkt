;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_8) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; given procedures

(define hanoi-moves     ; val: list of couples, each representing from_peg and to_peg of an optimal move
  (lambda (n)           ; n: disks number, positive
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec   ; val: list of couples
  (lambda (n s d t) ; n: disks number, s: source peg, d: destination peg, t: transition peg
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))

;; task 1

(define locate-bigger                    ; val: list of couples of integers, first number representing the peg and second representing number of disks in stack at a given k move
  (lambda (n k s d t s-num d-num t-num)  ; n: disks numbe
                                         ; k: move number
                                         ; s, d, t: source, destination, transition pegs
                                         ; s-num, d-num, t-num: disks number on s, d, t
    (let ((half (expt 2 (sub1 n))))
      (cond
        ; base case
        ((zero? n)
         (list (list s s-num) (list d d-num) (list t t-num))
         )
        ; recursive steps:
        ; case 1: bigger disk is still on s, we must move n-1 tower from s to t
        ((< k half)
         (locate-bigger (sub1 n) k s t d (add1 s-num) t-num d-num)
         )
        ; case 2: bigger disk is in d, we are moving n-1 tower from t to d
        (else
         (locate-bigger (sub1 n) (- k half) t d s t-num (add1 d-num) s-num)
         )
        )
      )
    ))

(define hanoi-disks                  ; val: list of couples (same as locate-bigger)
  (lambda (n k)                      ; n: disks number, k: move number
    (locate-bigger n k 1 2 3 0 0 0)
    ))

;; test

(hanoi-disks 3 0)      ;'((1 3) (3 0) (2 0)) 
(hanoi-disks 3 1)      ;'((3 0) (2 1) (1 2)) 
(hanoi-disks 3 2)      ;'((2 1) (1 1) (3 1))
(hanoi-disks 3 3)      ;'((1 1) (3 2) (2 0))
(hanoi-disks 3 4)      ;'((3 2) (2 1) (1 0))
(hanoi-disks 3 5)      ;'((2 1) (1 1) (3 1))
(hanoi-disks 3 6)      ;'((1 1) (3 0) (2 2))
(hanoi-disks 3 7)      ;'((3 0) (2 3) (1 0))
(hanoi-disks 5 13)     ;'((3 2) (2 1) (1 2))
(hanoi-disks 15 19705) ;'((3 4) (2 9) (1 2))
(hanoi-disks 15 32767) ;'((3 0) (2 15) (1 0)
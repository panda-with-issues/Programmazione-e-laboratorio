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

(define locate-bigger
  (lambda (n k s d t s-num d-num t-num)
    (let ((half-- (expt 2 (- n 2)))
          (offset (expt 2 (sub1 n))))
      (cond
        ; base case
        ((= n 1)
         (if (<= k 0)
             (list (list s (add1 s-num)) (list d d-num) (list t t-num))
             (list (list s s-num) (list d (add1 d-num)) (list t t-num))
             )
         )
        ; k is smaller than n-1 tower's middle move
        ((<= k half--)
         (locate-bigger (sub1 n) k s t d (add1 s-num) t-num d-num)
         )
        ; k is smaller than middle move
        ((< k (expt 2 (sub1 n)))
         (locate-bigger (sub1 n) (quotient k 2) s t d (add1 s-num) t-num d-num)
         )
        (else
         (locate-bigger (sub1 n) (- k offset) t d s t-num (add1 d-num) s-num)
         )
        )
      )
    ))

(define hanoi-disks
  (lambda (n k)
    (locate-bigger n k 1 2 3 0 0 0)
    ))

;; test

(hanoi-disks 3 0)      ;'((1 3) (3 0) (2 0)) ok
(hanoi-disks 3 1)      ;'((3 0) (2 1) (1 2)) ok
(hanoi-disks 3 2)      ;'((2 1) (1 1) (3 1)) ok
(hanoi-disks 3 3)      ;'((1 1) (3 2) (2 0)) non ok
(hanoi-disks 3 4)      ;'((3 2) (2 1) (1 0)) non ok
(hanoi-disks 3 5)      ;'((2 1) (1 1) (3 1)) non ok
(hanoi-disks 3 6)      ;'((1 1) (3 0) (2 2)) ok
(hanoi-disks 3 7)      ;'((3 0) (2 3) (1 0)) ok
;(hanoi-disks 5 13)     ;'((3 2) (2 1) (1 2))
;(hanoi-disks 15 19705) ;'((3 4) (2 9) (1 2))
;(hanoi-disks 15 32767) ;'((3 0) (2 15) (1 0)
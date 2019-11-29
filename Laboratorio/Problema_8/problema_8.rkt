;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_8) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define disk-position  ; val: list of three couples, first number is the stack's identifier, second one is the disks number stacked
  (lambda (n m k s s-num d d-num t t-num)
    (let ((half (- (expt 2 n) 1)))
      (cond ((= m k)
             (append (cons s s-num) (cons (cons d d-num) (cons t t-num)))
             )
            ((k >= half)
             (disk-position (- n 1) k s () d () t 1)
               ))